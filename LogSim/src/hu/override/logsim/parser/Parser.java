package hu.override.logsim.parser;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.impl.AndGate;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.impl.FlipFlopD;
import hu.override.logsim.component.impl.FlipFlopJK;
import hu.override.logsim.component.impl.Gnd;
import hu.override.logsim.component.impl.Inverter;
import hu.override.logsim.component.impl.Led;
import hu.override.logsim.component.impl.Mpx;
import hu.override.logsim.component.impl.OrGate;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.component.impl.SevenSegmentDisplay;
import hu.override.logsim.component.impl.Toggle;
import hu.override.logsim.component.impl.Vcc;
import hu.override.logsim.exception.CircuitAlreadyExistsException;
import hu.override.logsim.exception.InvalidCircuitDefinitionException;
import hu.override.logsim.exception.UnknownComponentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Minden komponens-névhez eltároljuk a bemeneteket, késõbbi feldolgozás miatt.
     */
    private static final HashMap<String, Class<? extends AbstractComponent>> availableComponents;
    /**
     * Regex minta egy leíró-sor feldolgozásához
     */
    private static Pattern componentPattern = Pattern.compile("(.*?)\\s*=\\s*(.*?)\\((.*?)\\)");
    /**
     * Regex minta egy komponens bemeneteinek a feldolgozásához
     */
    private static Pattern inputPattern = Pattern.compile("(.*?)(?:\\[([0-9]+)\\])?");
    private HashMap<String, String[]> inputs = new HashMap<String, String[]>();
    /**
     * Egy számláló, hogy a vcc és gnd komponenseknek eltérõ változónevet tudjunk adni.
     */
    private int constComps = 0;

    static {
        /**
         * Feldolgozó által ismert komponensek listája.
         */
        availableComponents = new HashMap<String, Class<? extends AbstractComponent>>(5);
        availableComponents.put("and", AndGate.class);
        availableComponents.put("or", OrGate.class);
        availableComponents.put("not", Inverter.class);
        availableComponents.put("toggle", Toggle.class);
        availableComponents.put("seqgen", SequenceGenerator.class);
        availableComponents.put("led", Led.class);
        availableComponents.put("gnd", Gnd.class);
        availableComponents.put("vcc", Vcc.class);
        availableComponents.put("7seg", SevenSegmentDisplay.class);
        availableComponents.put("mpx", Mpx.class);
        availableComponents.put("jk", FlipFlopJK.class);
        availableComponents.put("d", FlipFlopD.class);
    }

    /**
     * Létrehoz egy áramkört az argumentumokban megadott komponensekbõl
     * (olyan, mintha mindegyik paraméter egy leíró fájl egy sora lenne)
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
                circuit.addComponent(createComponent(componentName, variableName,
                        arguments == null ? 0 : arguments.length));
            } catch (UnknownComponentException ex) {
                System.err.println("nincs ilyen komponens!");
            }
        } else {
            System.out.println("NOT FOUND!!!");
        }
    }

    protected AbstractComponent createComponent(String componentName, String variableName, int inputPinsCount)
            throws UnknownComponentException {
        Class<? extends AbstractComponent> clazz = availableComponents.get(componentName);
        if (clazz != null) {
            AbstractComponent c = null;
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
                AbstractComponent component = circuit.getComponentByName(var);
                if (component instanceof SequenceGenerator) {
                    SequenceGenerator seqGen = (SequenceGenerator) component;
                    Value[] sequence = new Value[arguments.length];
                    for (int i = 0; i < arguments.length; i++) {
                        arg = arguments[i];
                        if (!arg.equals("0") && !arg.equals("1")) {
                            throw new InvalidCircuitDefinitionException("SeqGen kapott 1-estõl és 0-ástól különbözõ dolgot!");
                        } else {
                            sequence[i] = arg.equals("1") ? Value.TRUE : Value.FALSE;
                        }
                    }
                    seqGen.setValues(sequence);
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
                                AbstractComponent c = circuit.getComponentByName(matcher.group(1));
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
