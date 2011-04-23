package logsim;

import java.awt.Point;
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
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Composite;
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.model.component.impl.FlipFlopD;
import logsim.model.component.impl.FlipFlopJK;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Mpx;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;

/**
 * �ramk�r �rtelmez� objektum, feladata, hogy a param�terk�nt �tadott, illetve
 * f�jlban elhelyezett komponenseket �rtelmezze, a kapcsolatokat felt�rk�pezze,
 * elv�gezze az �sszek�ttet�seket, �s ez�ltal fel�p�tse az �ramk�rt.
 *
 * @author balint
 */
public class Parser {

    /**
     * A le�r�b�l l�trehozott �ramk�r.
     */
    private Circuit circuit;
    /**
     * Regex minta egy komponens-sor feldolgoz�s�hoz
     */
    private static Pattern componentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)");
    /**
     * Regex minta egy legfels� szint� komponens-sor feldolgoz�s�hoz
     */
    private static Pattern topLevelComponentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)\\s*\\{\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*\\}");
    /**
     * Regex minta egy kompozit kezdethez
     */
    private static Pattern compositeStartPattern = Pattern.compile("\\s*composite\\s*(.*?)\\((.*?)\\)\\s*\\{", Pattern.CASE_INSENSITIVE);
    /**
     * Regex minta egy kompozit v�ghez
     */
    private static Pattern compositeEndPattern = Pattern.compile("\\s*\\}\\s*\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
    /**
     * Komponensek list�ja n�v szerint.
     */
    private Map<String, Composite> composites = new HashMap<String, Composite>();
    /**
     * Kompozitokban l�v� komponensek param�ter list�ja.
     */
    private Map<Composite, Map<AbstractComponent, String[]>> parameters = new HashMap<Composite, Map<AbstractComponent, String[]>>();
    private Map<AbstractComponent, Point> positions = new HashMap<AbstractComponent, Point>();

    /**
     * L�trehoz egy �ramk�rt a megadott f�jlb�l
     *
     * @param file f�jl ami tartalmazza a komponenseket �jsorral elv�lasztva
     * @return l�trehozott �ramk�r
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
            // ex.printStackTrace(System.err);
        } catch (IOException ex) {
            // ex.printStackTrace(System.err);
        }

        return null;
    }

    private AbstractComponent parseComponent(String variableName,
            String componentName, String argumentsStr, Composite composite) {
        String arguments[];

        //System.out.println(variableName + " - " + componentName);

        if (!parameters.containsKey(composite)) {
            parameters.put(composite, new HashMap<AbstractComponent, String[]>());
        }

        Map<AbstractComponent, String[]> argumentsMap = parameters.get(composite);

        //ComponentPin[] pins = null;
        if (argumentsStr.length() == 0) {
            arguments = null;
        } else {
            arguments = argumentsStr.split("\\s*,\\s*");
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
        } else if (componentName.equalsIgnoreCase("and")) {
            component = new AndGate(arguments.length, variableName);
        } else if (componentName.equalsIgnoreCase("mpx")) {
            component = new Mpx(variableName);
        } else if (componentName.equalsIgnoreCase("inv")) {
            component = new Inverter(variableName);
        } else if (componentName.equalsIgnoreCase("led")) {
            component = new Led(variableName);
        } else if (componentName.equalsIgnoreCase("7seg")) {
            component = new SevenSegmentDisplay(variableName);
        } else if (componentName.equalsIgnoreCase("node")) {
            component = new Node(Integer.parseInt(arguments[1]), variableName);
            // csak 1 elem� t�mb kell!
            arguments = Arrays.copyOf(arguments, 1);
        } else if (componentName.equalsIgnoreCase("scope")) {
            component = new Scope(Integer.parseInt(arguments[1]), variableName);
            arguments = Arrays.copyOf(arguments, 1);
        } else if (componentName.equalsIgnoreCase("seqgen")) {
            SequenceGenerator sg = new SequenceGenerator(variableName);
            if (arguments != null) {
                Value[] values = new Value[arguments[0].length()];
                for (int i = 0; i < arguments[0].length(); i++) {
                    if (arguments[0].charAt(i) == '1') {
                        values[i] = Value.TRUE;
                    } else if (arguments[0].charAt(i) == '0') {
                        values[i] = Value.FALSE;
                    } else {
                        values[i] = null;
                    }
                }
                sg.setValues(values);
            }
            component = sg;
            arguments = null;
        } else if (componentName.equalsIgnoreCase("toggle")) {
            component = new Toggle(variableName);
        } else if (componentName.equalsIgnoreCase("ffd")) {
            component = new FlipFlopD(variableName);
        } else if (componentName.equalsIgnoreCase("ffjk")) {
            component = new FlipFlopJK(variableName);
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

    /**
     * Egy olyan komponens-sor feldolgoz�sa a f�jlban, ami a legfels� szinten szerepel,
     * azaz a kompozit amiben szerepel az az �ramk�r.
     *
     * @param matcher
     * @param circuit
     * @return
     */
    private AbstractComponent parseTopLevelComponentFromLine(Matcher matcher, Circuit circuit) {
        String variableName = matcher.group(1);
        String componentName = matcher.group(2).toLowerCase();
        String argumentsStr = matcher.group(3).trim();
        int x = Integer.parseInt(matcher.group(4));
        int y = Integer.parseInt(matcher.group(5));

        AbstractComponent ac = parseComponent(variableName, componentName, argumentsStr, circuit);

        positions.put(ac, new Point(x, y));

        return ac;
    }

    /**
     * Egy komponens-sor feldolgoz�sa a f�jlban
     * 
     * @param matcher regex tal�latok
     * @param composite kompozit ahova besz�rjuk
     * @return l�trehozott komponens
     */
    private AbstractComponent parseComponentFromLine(Matcher matcher, Composite composite) {
        String variableName = matcher.group(1);
        String componentName = matcher.group(2).toLowerCase();
        String argumentsStr = matcher.group(3).trim();

        return parseComponent(variableName, componentName, argumentsStr, composite);
    }

    /**
     * Bementr�l feldolgoz�s
     *
     * @param br bemeneti stream
     * @throws IOException
     */
    private void parse(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = topLevelComponentPattern.matcher(line);
            if (matcher.matches()) {
                parseTopLevelComponentFromLine(matcher, circuit);
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

    /**
     * Komponens hozz�ad�sa a kompozithoz
     *
     * @param composite
     * @param lines
     * @param inputs
     * @param outputs
     */
    private void addComponentsToComposite(Composite composite, List<String> lines,
            String[] inputs, String[] outputs) {
        for (String line : lines) {
            Matcher matcher = componentPattern.matcher(line);
            if (matcher.matches()) {
                parseComponentFromLine(matcher, composite);
            }
        }

        composite.connectComponents(parameters.get(composite), inputs, outputs);
    }

    /**
     * Komponens poz�ci�j�nak a lek�rdez�se
     *
     * @param ac komponens
     * @return poz�ci�
     */
    public Point getPosition(AbstractComponent ac) {
        return positions.get(ac);
    }

    /**
     * Vezet�k referenciapontjainak a lek�rdez�se
     *
     * @param wire vezet�k
     * @return referenciapontok
     */
    public Point[] getReferencePoints(Wire wire) {
        return null;
    }
}
