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
import logsim.model.component.Pin;
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
 * Áramkör értelmezõ objektum, feladata, hogy a paraméterként átadott, illetve
 * fájlban elhelyezett komponenseket értelmezze, a kapcsolatokat feltérképezze,
 * elvégezze az összeköttetéseket, és ezáltal felépítse az áramkört.
 *
 * @author balint
 */
public class Parser {

    /**
     * A leíróból létrehozott áramkör.
     */
    private Circuit circuit;
    /**
     * Regex minta egy komponens-sor feldolgozásához
     */
    private static Pattern componentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)");
    /**
     * Regex minta egy legfelsõ szintû komponens-sor feldolgozásához
     */
    private static Pattern topLevelComponentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.*?)\\s*\\((.*?)\\)\\s*\\{\\s*([0-9]+)\\s+([0-9]+)\\s*\\}");
    /**
     * Regex minta a legfelsõ szintû komponensek argumentumaihoz (vezetékek referencia pontjai miatt)
     */
    private static Pattern topLevelArgumentPattern = Pattern.compile("(.*?)\\s*(?:\\{((?:\\s*\\(\\s*[0-9]+\\s+[0-9]+\\s*\\)\\s*)+)\\})?");
    private static Pattern refPointPattern = Pattern.compile("(?:\\s*\\(\\s*([0-9]+)\\s+([0-9]+)\\s*\\)\\s*)");
    /**\{((?:
     * Regex minta egy kompozit kezdethez
     */
    private static Pattern compositeStartPattern = Pattern.compile("\\s*composite\\s*(.*?)\\((.*?)\\)\\s*\\{", Pattern.CASE_INSENSITIVE);
    /**
     * Regex minta egy kompozit véghez
     */
    private static Pattern compositeEndPattern = Pattern.compile("\\s*\\}\\s*\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
    /**
     * Komponensek listája név szerint.
     */
    private Map<String, Composite> composites = new HashMap<String, Composite>();
    /**
     * Kompozitokban lévõ komponensek paraméter listája.
     */
    private Map<Composite, Map<AbstractComponent, String[]>> parameters = new HashMap<Composite, Map<AbstractComponent, String[]>>();
    private Map<AbstractComponent, Point> positions = new HashMap<AbstractComponent, Point>();
    private Map<AbstractComponent, List<Point>[]> wireRefPoints = new HashMap<AbstractComponent, List<Point>[]>();

    /**
     * Létrehoz egy áramkört a megadott fájlból
     *
     * @param file fájl ami tartalmazza a komponenseket újsorral elválasztva
     * @return létrehozott áramkör
     */
    public Circuit parse(File file) {
        circuit = new Circuit();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

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

        if (!parameters.containsKey(composite)) {
            parameters.put(composite, new HashMap<AbstractComponent, String[]>());
        }

        Map<AbstractComponent, String[]> argumentsMap = parameters.get(composite);

        if (argumentsStr.length() == 0) {
            arguments = null;
        } else {
            arguments = argumentsStr.split("\\s*,\\s*");
        }

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
            // csak 1 elemû tömb kell!
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

    /**
     * Egy olyan komponens-sor feldolgozása a fájlban, ami a legfelsõ szinten szerepel,
     * azaz a kompozit amiben szerepel az az áramkör. Itt a pozíció információt is feldolgozzuk!
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

        // itt szedjük szét az argumentsStr stringet
        String plainArgumentsStr;

        List<Point>[] refPoints = null;

        if (argumentsStr.length() != 0) {
            String[] arguments = argumentsStr.split("\\s*,\\s*");
            String[] plainArguments = new String[arguments.length];

            refPoints = new List[arguments.length];

            for (int i = 0; i < arguments.length; i++) {
                Matcher m = topLevelArgumentPattern.matcher(arguments[i]);
                if (m.matches()) {
                    plainArguments[i] = m.group(1);
                    String refs = m.group(2);
                    if (refs != null) {
                        refPoints[i] = new ArrayList<Point>();
                        Matcher m2 = refPointPattern.matcher(refs);
                        while (m2.find()) {
                            refPoints[i].add(new Point(Integer.parseInt(m2.group(1)), Integer.parseInt(m2.group(2))));
                        }
                    }
                }
            }

            // majd rakjuk össze
            StringBuilder sb = new StringBuilder();
            for (String str : plainArguments) {
                sb.append(str);
                sb.append(",");
            }
            plainArgumentsStr = sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            plainArgumentsStr = argumentsStr;
        }

        AbstractComponent ac = parseComponent(variableName, componentName, plainArgumentsStr, circuit);

        positions.put(ac, new Point(x, y));
        wireRefPoints.put(ac, refPoints);

        return ac;
    }

    /**
     * Egy komponens-sor feldolgozása a fájlban
     * 
     * @param matcher regex találatok
     * @param composite kompozit ahova beszúrjuk
     * @return létrehozott komponens
     */
    private AbstractComponent parseComponentFromLine(Matcher matcher, Composite composite) {
        String variableName = matcher.group(1);
        String componentName = matcher.group(2).toLowerCase();
        String argumentsStr = matcher.group(3).trim();

        return parseComponent(variableName, componentName, argumentsStr, composite);
    }

    /**
     * Bementrõl feldolgozás
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

    /**
     * Komponens hozzáadása a kompozithoz
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
     * Komponens pozíciójának a lekérdezése
     *
     * @param ac komponens
     * @return pozíció
     */
    public Point getPosition(AbstractComponent ac) {
        return positions.get(ac);
    }

    /**
     * Vezeték referenciapontjainak a lekérdezése
     *
     * @param wire vezeték
     * @return referenciapontok
     */
    public List<Point> getReferencePoints(Pin pin) {
        List<Point>[] tmp = wireRefPoints.get(pin.getComponent());
        if (tmp != null) {
            return tmp[pin.getPin() - 1];
        }
        return null;
    }
}
