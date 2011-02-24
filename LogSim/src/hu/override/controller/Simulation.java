package hu.override.controller;

import hu.override.Circuit;
import hu.override.SequenceGeneratorStepper;
import hu.override.view.View;

/**
 *
 * @author balint
 */
public class Simulation extends Thread implements Controller {

    private boolean shouldRun;
    private Circuit circuit;
    private int counter;
    private final View view;
    private final Object synchObj = new Object();
    private final SequenceGeneratorStepper seqGenStepper;

    public Simulation(Circuit circuit, View view) {
        this.circuit = circuit;
        this.view = view;
        this.view.setController(this);
        this.seqGenStepper = new SequenceGeneratorStepper(this);
    }

    @Override
    public void run() {
        // amikor elindul a szimul�ci�, akkor a steppert is ind�tsuk el.
        seqGenStepper.start();

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
                System.out.println("Nincs stacion�rius �llapot!");
                break;
            }
            // GUI rajzol�s
            view.update(circuit);

            // lefutott egy ciklus, v�runk, hogy lesz-e v�ltoz�s
            try {
                synchronized (synchObj) {
                    synchObj.wait();
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Megv�ltozott valamelyik jelforr�s, szimul�ci� mehet �jb�l
     */
    public void sourcesChanged() {
        synchronized (synchObj) {
            synchObj.notify();
        }
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }
}
