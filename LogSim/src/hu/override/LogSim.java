package hu.override;

import hu.override.controller.CircuitController;
import hu.override.exception.CircuitAlreadyExistsException;
import hu.override.exception.InvalidCircuitDefinitionException;
import hu.override.parser.Parser;
import hu.override.view.ConsoleView;
import hu.override.view.View;
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


        try {
            Circuit c = new Parser().parse(new File("test.txt"));
            View v = new ConsoleView();
            CircuitController cc = new CircuitController(c, v);
            cc.start();
            //c.list();
        } catch (CircuitAlreadyExistsException ex) {
            ex.printStackTrace(System.err);
        } catch (InvalidCircuitDefinitionException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
