package hu.override.parser;

import hu.override.Circuit;
import hu.override.exception.UnknownComponentException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author balint
 */
public class Parser {

    Pattern componentPattern = Pattern.compile("(.*?)\\s*=\\s*(.*?)\\((.*?)\\)");

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
                circuit.addComponent(componentName, variableName, arguments);
            } catch (UnknownComponentException ex) {
                System.err.println("nincs ilyen komponens!");
            }
        } else {
            System.out.println("NOT FOUND!!!");
        }
    }
}
