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
            // el�sz�r v�runk, mert most minden jelgener�tor kiadja az els� bitet
            // teh�t ennek is hagyni kell kicsit futni
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
