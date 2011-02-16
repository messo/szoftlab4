package hu.override.parser;

import hu.override.Circuit;
import hu.override.exception.UnknownComponentException;
import java.io.BufferedReader;
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

    public Circuit parse(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            String tmp;

            Matcher matcher;
            String variableName;
            String componentName;
            String[] arguments;

            Circuit circuit = new Circuit();

            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                matcher = componentPattern.matcher(line);
                if (matcher.matches()) {
                    variableName = matcher.group(1);
                    componentName = matcher.group(2).toLowerCase();
                    tmp = matcher.group(3).trim();

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

            return circuit;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return null;
    }
}