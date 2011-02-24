package hu.override.logsim;

import hu.override.logsim.controller.Simulation;
import hu.override.logsim.exception.CircuitAlreadyExistsException;
import hu.override.logsim.exception.InvalidCircuitDefinitionException;
import hu.override.logsim.parser.Parser;
import hu.override.logsim.view.ConsoleView;
import hu.override.logsim.view.View;
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
            Simulation simulation = new Simulation(c, v);
            simulation.start();
        } catch (CircuitAlreadyExistsException ex) {
            ex.printStackTrace(System.err);
        } catch (InvalidCircuitDefinitionException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
