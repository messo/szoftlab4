package hu.override;

import hu.override.parser.Parser;
import java.io.File;

/**
 *
 * @author balint
 */
public class LogSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circuit c = new Parser().parse(new File("test.txt"));
        c.list();
    }
}
