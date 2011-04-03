package logsim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logsim.model.Circuit;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Composite;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * Áramkör értelmezõ objektum, feladata, hogy a paraméterként átadott, illetve
 * fájlban elhelyezett komponenseket értelmezze, a kapcsolatokat feltérképezze,
 * elvégezze az összeköttetéseket, és ezáltal felépítse az áramkört.
 * Fontos, hogy egy ilyen objektum csak egyszer használható, új áramkörhöz, újat
 * kell létrehozni.
 *
 * @author balint
 */
public class Parser {

    /**
     * A leíróból létrehozott áramkör.
     */
    private Circuit circuit;
    /**
     * Regex minta egy leíró-sor feldolgozásához
     */
    private static Pattern componentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)");
    private static Pattern compositeStartPattern = Pattern.compile("\\s*composite\\s*(.*?)\\((.*?)\\)\\s*\\{", Pattern.CASE_INSENSITIVE);
    private static Pattern compositeEndPattern = Pattern.compile("\\s*\\}\\s*\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
    //private Map<String, String[]> inputs = new HashMap<String, String[]>();
    private Map<String, Composite> composites = new HashMap<String, Composite>();
    /**
     * Egy számláló, hogy a vcc és gnd komponenseknek eltérõ változónevet tudjunk adni.
     */
    private int constComps = 0;
    private Map<Composite, Map<AbstractComponent, String[]>> parameters = new HashMap<Composite, Map<AbstractComponent, String[]>>();

    public static class ComponentPin {

        private String componentName;
        private int pinIndex;

        public ComponentPin(String componentName, int pinIndex) {
            this.componentName = componentName;
            this.pinIndex = pinIndex;
        }

        public String getComponentName() {
            return componentName;
        }

        public int getPinIndex() {
            return pinIndex;
        }
    }

    /**
     * Létrehoz egy áramkört a megadott fájlból

     * @param file fájl ami tartalmazza a komponenseket újsorral elválasztva
     * @return
     */
    public Circuit parse(File file) {
        circuit = new Circuit();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            parameters.put(circuit, new HashMap<AbstractComponent, String[]>());

            parse(br);

            circuit.connectComponents(parameters.get(circuit), null, null);

            return circuit;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return null;
    }

    private AbstractComponent parseComponentFromLine(Matcher matcher, Composite composite) {
        String variableName = matcher.group(1);
        String componentName = matcher.group(2).toLowerCase();
        String tmp = matcher.group(3).trim();
        String arguments[];

        System.out.println(variableName + " - " + componentName);

        if (!parameters.containsKey(composite)) {
            parameters.put(composite, new HashMap<AbstractComponent, String[]>());
        }

        Map<AbstractComponent, String[]> argumentsMap = parameters.get(composite);

        //ComponentPin[] pins = null;
        if (tmp.length() == 0) {
            arguments = null;
        } else {
            arguments = tmp.split("\\s*,\\s*");
//            pins = new ComponentPin[arguments.length];
//            int i = 0;
//            for (String arg : arguments) {
//                Matcher paramMatcher = inputPattern.matcher(arg);
//                if (paramMatcher.matches()) {
//                    pins[i++] = new ComponentPin(paramMatcher.group(1), paramMatcher.group(2) != null ? Integer.parseInt(paramMatcher.group(2)) : 1);
//                }
//            }
        }

        // KOMPONENS LÉTREHOZÁSA

        // TODO :
        // * scope-nál nem kell utsó
        // * seqgennél nem kell

        AbstractComponent component = null;
        if (componentName.equalsIgnoreCase("or")) {
            component = new OrGate(arguments.length, variableName);
        } else if (componentName.equalsIgnoreCase("inv")) {
            component = new Inverter(variableName);
        } else if (componentName.equalsIgnoreCase("led")) {
            component = new Led(variableName);
        } else if (componentName.equalsIgnoreCase("node")) {
            component = new Node(Integer.parseInt(arguments[1]), variableName);
            // csak 1 elemû tömb kell!
            arguments = Arrays.copyOf(arguments, 1);
        } else if (componentName.equalsIgnoreCase("toggle")) {
            component = new Toggle(variableName);
        } else if (composites.containsKey(componentName)) {
            // ha van ilyen nevû komponens, akkor azt ugye már létrehoztuk, most le kéne másolni
            Composite subComposite = composites.get(componentName).copy(variableName);
            component = subComposite;
        }
        // KOMPONENS LÉTREHOZVA!

        if (arguments != null) {
            argumentsMap.put(component, arguments);
        }

        component.addTo(composite);

        return component;
    }

    private void parse(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = componentPattern.matcher(line);
            if (matcher.matches()) {
                parseComponentFromLine(matcher, circuit);
            } else {
                Matcher matcher2 = compositeStartPattern.matcher(line);
                if (matcher2.matches()) {
                    String compositeType = matcher2.group(1).toLowerCase();
                    String tmp = matcher2.group(2).trim();
                    String[] inputs;
                    if (tmp.length() == 0) {
                        inputs = new String[0];
                    } else {
                        inputs = tmp.split("\\s*,\\s*");
                    }

                    List<String> lines = new ArrayList<String>();
                    do {
                        line = br.readLine();
                        Matcher matcher3 = compositeEndPattern.matcher(line);
                        if (matcher3.matches()) {
                            // Vége a kompozitnak.
                            tmp = matcher3.group(1).trim();
                            String[] outputs;
                            if (tmp.length() == 0) {
                                outputs = null;
                            } else {
                                outputs = tmp.split("\\s*,\\s*");
                            }
                            Composite composite = new Composite(compositeType, null, inputs.length, outputs.length);
                            composites.put(compositeType, composite);

                            addComponentsToComposite(composite, lines, inputs, outputs);

                            break;
                        } else {
                            // SZÁMOLNI, hogy egy bemenet hányszor van használva!
                            lines.add(line);
                        }
                    } while (true);
                }
            }
        }
    }

    private void addComponentsToComposite(Composite composite, List<String> lines,
            String[] inputs, String[] outputs) {
        System.out.println(composite.getClassName());

        for (String line : lines) {
            Matcher matcher = componentPattern.matcher(line);
            if (matcher.matches()) {
                parseComponentFromLine(matcher, composite);
            }
        }

        composite.connectComponents(parameters.get(composite), inputs, outputs);
    }
}
