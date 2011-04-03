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
 * �ramk�r �rtelmez� objektum, feladata, hogy a param�terk�nt �tadott, illetve
 * f�jlban elhelyezett komponenseket �rtelmezze, a kapcsolatokat felt�rk�pezze,
 * elv�gezze az �sszek�ttet�seket, �s ez�ltal fel�p�tse az �ramk�rt.
 * Fontos, hogy egy ilyen objektum csak egyszer haszn�lhat�, �j �ramk�rh�z, �jat
 * kell l�trehozni.
 *
 * @author balint
 */
public class Parser {

    /**
     * A le�r�b�l l�trehozott �ramk�r.
     */
    private Circuit circuit;
    /**
     * Regex minta egy le�r�-sor feldolgoz�s�hoz
     */
    private static Pattern componentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)");
    private static Pattern compositeStartPattern = Pattern.compile("\\s*composite\\s*(.*?)\\((.*?)\\)\\s*\\{", Pattern.CASE_INSENSITIVE);
    private static Pattern compositeEndPattern = Pattern.compile("\\s*\\}\\s*\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
    //private Map<String, String[]> inputs = new HashMap<String, String[]>();
    private Map<String, Composite> composites = new HashMap<String, Composite>();
    /**
     * Egy sz�ml�l�, hogy a vcc �s gnd komponenseknek elt�r� v�ltoz�nevet tudjunk adni.
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
     * L�trehoz egy �ramk�rt a megadott f�jlb�l

     * @param file f�jl ami tartalmazza a komponenseket �jsorral elv�lasztva
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

        // KOMPONENS L�TREHOZ�SA

        // TODO :
        // * scope-n�l nem kell uts�
        // * seqgenn�l nem kell

        AbstractComponent component = null;
        if (componentName.equalsIgnoreCase("or")) {
            component = new OrGate(arguments.length, variableName);
        } else if (componentName.equalsIgnoreCase("inv")) {
            component = new Inverter(variableName);
        } else if (componentName.equalsIgnoreCase("led")) {
            component = new Led(variableName);
        } else if (componentName.equalsIgnoreCase("node")) {
            component = new Node(Integer.parseInt(arguments[1]), variableName);
            // csak 1 elem� t�mb kell!
            arguments = Arrays.copyOf(arguments, 1);
        } else if (componentName.equalsIgnoreCase("toggle")) {
            component = new Toggle(variableName);
        } else if (composites.containsKey(componentName)) {
            // ha van ilyen nev� komponens, akkor azt ugye m�r l�trehoztuk, most le k�ne m�solni
            Composite subComposite = composites.get(componentName).copy(variableName);
            component = subComposite;
        }
        // KOMPONENS L�TREHOZVA!

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
                            // V�ge a kompozitnak.
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
                            // SZ�MOLNI, hogy egy bemenet h�nyszor van haszn�lva!
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
