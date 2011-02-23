package hu.override;

import hu.override.controller.Simulation;

/**
 *
 * @author balint
 */
public class SequenceGeneratorStepper extends Thread {

    private Simulation simulation;
    private boolean shouldRun;
    private long pause = 3000;

    public SequenceGeneratorStepper(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        shouldRun = true;
        while (shouldRun) {
            // elõször várunk, mert most minden jelgenerátor kiadja az elsõ bitet
            // tehát ennek is hagyni kell kicsit futni
            try {
                sleep(pause);
            } catch (InterruptedException ex) {
            }
            simulation.getCircuit().stepGenerators();
            simulation.sourcesChanged();
        }
    }
}
