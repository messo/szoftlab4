package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.controller.Controller;
import hu.override.logsim.exception.CircuitAlreadyExistsException;
import hu.override.logsim.exception.InvalidCircuitDefinitionException;
import hu.override.logsim.parser.Parser;
import hu.override.logsim.view.GuiView;
import hu.override.logsim.view.View;
import java.io.File;

/**
 *
 * @author balint
 */
public class LogSim implements Controller {

    Circuit circuit;
    Simulation simulation;
    View view;

    public LogSim() throws CircuitAlreadyExistsException,
            InvalidCircuitDefinitionException {
        circuit = new Parser().parse(new File("test.txt"));
        view = new GuiView(this);

        for (IsSource c : circuit.getSources()) {
            view.addSource(c);
        }
        for (IsDisplay c : circuit.getDisplays()) {
            view.addDisplay(c);
        }

        view.layoutDone();
    }

    public Simulation getFreshSimulation() {
        return new Simulation(circuit, this);
    }

    public void onCircuitUpdate() {
        view.update(circuit);
    }

    public void onStart() {
        simulation = getFreshSimulation();
        simulation.start();
    }

    public void onStop() {
        simulation.setState(Simulation.State.STOPPED);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            LogSim logSim = new LogSim();
        } catch (CircuitAlreadyExistsException ex) {
            ex.printStackTrace(System.err);
        } catch (InvalidCircuitDefinitionException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void onExit() {
        try {
            if (simulation != null) {
                // le�ll�tjuk a szimul�ci�t
                simulation.setState(Simulation.State.STOPPED);
                simulation.join();
                simulation.getStepperThread().join();
            }
        } catch (InterruptedException ex) {
        }
        System.exit(0);
    }
}