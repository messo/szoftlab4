package hu.override.logsim;

import hu.override.logsim.controller.Simulation;

/**
 *
 * @author balint
 */
public class SequenceGeneratorStepper extends Thread {

    private Simulation simulation;
    private boolean shouldRun;
    private long pause = 50;

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
            
            new Thread() {

                @Override
                public void run() {
                    simulation.getCircuit().stepGenerators();
                }
            }.start();
        }
    }
}
