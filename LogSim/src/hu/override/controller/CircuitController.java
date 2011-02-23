package hu.override.controller;

import hu.override.Circuit;
import hu.override.view.View;

/**
 *
 * @author balint
 */
public class CircuitController extends Thread implements Controller {

    private boolean shouldRun;
    private final Circuit circuit;
    private int counter;
    private final View view;
    private final Object synchObj = new Object();
    private long pause = 3000;

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
            if (counter == 100) {
                System.out.println("HALÁÁÁÁÁL!");
                break;
            }
            circuit.stepGenerators();
            // GUI rajzolás
            view.update(circuit);
            try {
                synchronized (synchObj) {
                    synchObj.wait(pause);
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public void onUserAction() {
        System.out.println("onUserAction");
        synchronized (synchObj) {
            synchObj.notify();
        }
    }
}
