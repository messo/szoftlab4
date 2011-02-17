package hu.override.parser;

import hu.override.Circuit;
import hu.override.component.AndGate;
import hu.override.component.Component;
import hu.override.component.Inverter;
import hu.override.component.Led;
import hu.override.component.OrGate;
import hu.override.component.SequenceGenerator;
import hu.override.component.Toggle;
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

    private static final HashMap<String, Class<? extends Component>> availableComponents;
    private Pattern componentPattern = Pattern.compile("(.*?)\\s*=\\s*(.*?)\\((.*?)\\)");

    static {
        availableComponents = new HashMap<String, Class<? extends Component>>(5);
        availableComponents.put("and", AndGate.class);
        availableComponents.put("or", OrGate.class);
        availableComponents.put("not", Inverter.class);
        availableComponents.put("toggle", Toggle.class);
        availableComponents.put("seqgen", SequenceGenerator.class);
        availableComponents.put("led", Led.class);
    }

    /**
     * Létrehoz egy áramkört az argumentumokban megadott komponensekbõl
     *
     * @param content a komponensek, amiket hozzá akarunk adni
     * @return
     */
    public Circuit parse(String... content) {
        Circuit circuit = new Circuit();

        for (String line : content) {
            parseLine(line, circuit);
        }

        return circuit;
    }

    /**
     * Létrehoz egy áramkört a megadott fájlból

     * @param file fájl ami tartalmazza a komponenseket újsorral elválasztva
     * @return
     */
    public Circuit parse(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Circuit circuit = new Circuit();

            while ((line = br.readLine()) != null) {
                parseLine(line, circuit);
            }

            return circuit;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return null;
    }

    protected void parseLine(String line, Circuit circuit) {
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

            try {
                circuit.addComponent(createComponent(componentName, variableName, arguments));
            } catch (UnknownComponentException ex) {
                System.err.println("nincs ilyen komponens!");
            }
        } else {
            System.out.println("NOT FOUND!!!");
        }
    }

    protected Component createComponent(String componentName, String variableName, String[] arguments)
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
                c.setName(variableName);
                c.init(arguments);
                return c;
            }
        } else {
            throw new UnknownComponentException();
        }

        return null;
    }
}
