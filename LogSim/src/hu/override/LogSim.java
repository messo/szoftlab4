package hu.override;

import hu.override.parser.Parser;

/**
 *
 * @author balint
 */
public class LogSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circuit c = new Parser().parse("test.txt");
        c.list();
    }
}
