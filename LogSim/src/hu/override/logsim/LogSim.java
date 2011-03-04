package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.DisplayComponent;
import hu.override.logsim.component.SourceComponent;
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

    Simulation simulation = new Simulation(this);
    View view;

    public LogSim() throws CircuitAlreadyExistsException,
            InvalidCircuitDefinitionException {
        simulation.loadCircuitFromFile("test.txt");
        view = new GuiView(this);

        for (SourceComponent c : simulation.getCircuit().getSources()) {
            view.addSource(c);
        }
        for (DisplayComponent c : simulation.getCircuit().getDisplays()) {
            view.addDisplay(c);
        }

        view.layoutDone();
    }

    public void onCircuitUpdate() {
        view.update(simulation.getCircuit());
    }

    public void onStart() {
        simulation.start();
    }

    public void onStop() {
        //simulation.setState(Simulation.State.STOPPED);
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
        /*try {
            if (simulation != null) {
                // leállítjuk a szimulációt
                simulation.setState(Simulation.State.STOPPED);
                simulation.join();
                simulation.getStepperThread().join();
            }
        } catch (InterruptedException ex) {
        }*/
        System.exit(0);
    }
}
