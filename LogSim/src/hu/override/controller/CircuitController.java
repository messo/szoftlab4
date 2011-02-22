package hu.override.controller;

import hu.override.Circuit;
import hu.override.view.View;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author balint
 */
public class CircuitController extends Thread implements Controller {

    private boolean shouldRun;
    private final Circuit circuit;
    private int counter;
    private final View view;

    public CircuitController(Circuit circuit, View view) {
        this.circuit = circuit;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void run() {
        shouldRun = true;
        while (shouldRun) {
            counter = 0;
            while (counter < 100) {
                circuit.clearDirtyFlag();
                circuit.simulate();
                if (!circuit.isChanged()) {
                    break;
                }
                counter++;
            }
            if( counter == 100 ) {
                System.out.println("HALÁÁÁÁÁL!");
                break;
            }
            circuit.stepGenerators();
            // GUI rajzolás
            view.update(circuit);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }
}
