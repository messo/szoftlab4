package hu.override.parser;

import hu.override.Circuit;
import hu.override.component.AndGate;
import hu.override.component.Component;
import hu.override.component.Gnd;
import hu.override.component.Inverter;
import hu.override.component.Led;
import hu.override.component.OrGate;
import hu.override.component.SequenceGenerator;
import hu.override.component.Toggle;
import hu.override.component.Vcc;
import hu.override.exception.CircuitAlreadyExistsException;
import hu.override.exception.InvalidCircuitDefinitionException;
import hu.override.exception.UnknownComponentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author balint
 */
public class Parser {

    private Circuit circuit;
    private static final HashMap<String, Class<? extends Component>> availableComponents;
    private static Pattern componentPattern = Pattern.compile("(.*?)\\s*=\\s*(.*?)\\((.*?)\\)");
    private static Pattern inputPattern = Pattern.compile("(.*?)(?:\\[([0-9]+)\\])?");
    private HashMap<String, String[]> inputs = new HashMap<String, String[]>();
    private int constComps = 0;

    static {
        availableComponents = new HashMap<String, Class<? extends Component>>(5);
        availableComponents.put("and", AndGate.class);
        availableComponents.put("or", OrGate.class);
        availableComponents.put("not", Inverter.class);
        availableComponents.put("toggle", Toggle.class);
        availableComponents.put("seqgen", SequenceGenerator.class);
        availableComponents.put("led", Led.class);
        availableComponents.put("gnd", Gnd.class);
        availableComponents.put("vcc", Vcc.class);
    }

    /**
     * Létrehoz egy áramkört az argumentumokban megadott komponensekbõl
     *
     * @param content a komponensek, amiket hozzá akarunk adni
     * @return
     */
    public Circuit parse(String... content) throws CircuitAlreadyExistsException,
            InvalidCircuitDefinitionException {
        init();

        for (String line : content) {
            parseLine(line);
        }

        setArguments();

        return circuit;
    }

    /**
     * Létrehoz egy áramkört a megadott fájlból

     * @param file fájl ami tartalmazza a komponenseket újsorral elválasztva
     * @return
     */
    public Circuit parse(File file) throws CircuitAlreadyExistsException,
            InvalidCircuitDefinitionException {
        init();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                parseLine(line);
            }

            setArguments();

            return circuit;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return null;
    }

    private void init() throws CircuitAlreadyExistsException {
        if (circuit == null) {
            circuit = new Circuit();
        } else {
            throw new CircuitAlreadyExistsException();
        }
    }

    protected void parseLine(String line) {
        // System.out.println(line);
        Matcher matcher = componentPattern.matcher(line);
        if (matcher.matches()) {
            String variableName = matcher.group(1);
            String componentName = matcher.group(2).toLowerCase();
            String tmp = matcher.group(3).trim();
            String arguments[];

            //System.out.println(variableName + " - " + componentName);

            if (tmp.length() == 0) {
                arguments = null;
            } else {
                arguments = tmp.split("\\s*,\\s*");
            }
            inputs.put(variableName, arguments);

            try {
                circuit.addComponent(createComponent(componentName, variableName, arguments.length));
            } catch (UnknownComponentException ex) {
                System.err.println("nincs ilyen komponens!");
            }
        } else {
            System.out.println("NOT FOUND!!!");
        }
    }

    protected Component createComponent(String componentName, String variableName, int inputPinsCount)
            throws UnknownComponentException {
        Class<? extends Component> clazz = availableComponents.get(componentName);
        if (clazz != null) {
            Component c = null;
            try {
                c = clazz.newInstance();
            } catch (Exception ex) {
                System.err.println("Nem sikerüt példányosítani a komponenst!");
                ex.printStackTrace(System.err);
            }

            if (c != null) {
                c.setInputPinsCount(inputPinsCount);
                c.setName(variableName);
                return c;
            }
        } else {
            throw new UnknownComponentException();
        }

        return null;
    }

    private void setArguments() throws InvalidCircuitDefinitionException {
        String[] arguments;
        String arg;
        for (String var : inputs.keySet()) {
            arguments = inputs.get(var);
            // ha nincs argumentum, akkor következõ komponens
            if (arguments == null) {
                continue;
            }
            // ha van, akkor egyesével feldolgozzuk és hozzáadjuk a komponenshez.
            try {
                Component component = circuit.getComponentByName(var);
                if (component instanceof SequenceGenerator) {
                    SequenceGenerator seqGen = (SequenceGenerator) component;
                    boolean[] sequence = new boolean[arguments.length];
                    for (int i = 0; i < arguments.length; i++) {
                        arg = arguments[i];
                        if (!arg.equals("0") && !arg.equals("1")) {
                            throw new InvalidCircuitDefinitionException("SeqGen kapott 1-estõl és 0-ástól különbözõ dolgot!");
                        } else {
                            sequence[i] = arg.equals("1") ? true : false;
                        }
                    }
                    seqGen.setSequence(sequence);
                } else {
                    for (int i = 0; i < arguments.length; i++) {
                        arg = arguments[i];
                        if (arg.equals("0")) {
                            circuit.getComponentByName(var).setInput(i,
                                    circuit.addComponent(
                                    createComponent("gnd", "0" + (constComps++), 0)));
                        } else if (arg.equals("1")) {
                            circuit.getComponentByName(var).setInput(i,
                                    circuit.addComponent(
                                    createComponent("vcc", "0" + (constComps++), 0)));
                        } else {
                            Matcher matcher = inputPattern.matcher(arg);
                            if (matcher.matches()) {
                                Component c = circuit.getComponentByName(matcher.group(1));
                                String index = matcher.group(2);
                                if (index == null) {
                                    circuit.getComponentByName(var).setInput(i, c);
                                } else {
                                    circuit.getComponentByName(var).setInput(i, c, Integer.parseInt(index));
                                }
                            }
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                throw new InvalidCircuitDefinitionException("Nem szám van a bemenet indexében!");
            } catch (UnknownComponentException ex) {
                throw new InvalidCircuitDefinitionException("Nem létezik a komponens!");
            }
        }

    }
}
