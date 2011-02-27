package hu.override.logsim;

import hu.override.logsim.component.IsSource;
import hu.override.logsim.controller.Controller;
import hu.override.logsim.parser.SourceWriter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author balint
 */
public class Simulation extends Thread {

    /**
     * Szimuláció állapotait írja le
     */
    public static enum State {

        /**
         * Szimuláció éppen dolgozik, egy konkrét jelforrás-kombinációt alkalmazva dolgoztatja az áramkört
         */
        WORKING,
        /**
         * Szimuláció leállt, ahhoz, hogy bármi történjen az áramkörre újra kell indítani.
         */
        STOPPED,
        /**
         * Szimuláció szüneteltetve van, a következõ jelforrás változásig.
         */
        PAUSED
    }
    private State currentState;
    private static final int cycleLimit = 100;
    private boolean shouldRun;
    private Circuit circuit;
    private AtomicInteger counter = new AtomicInteger();
    private final Controller controller;
    private final Object synchObj = new Object();
    private final Object lock = new Object();
    private SequenceGeneratorStepper seqGenStepper;

    public Simulation(Circuit circuit, Controller controller) {
        super("Simulation");
        this.circuit = circuit;
        this.circuit.setSimulation(this);
        this.controller = controller;
    }

    @Override
    public void run() {
        // amikor elindul a szimuláció, akkor a steppert is indítsuk el.
        seqGenStepper = new SequenceGeneratorStepper(this);
        seqGenStepper.start();

        shouldRun = true;
        while (shouldRun) {
            synchronized (lock) {
                counter.set(0);
                while (counter.getAndIncrement() < cycleLimit) {
                    circuit.doEvaluationCycle();
                    if (circuit.isStable()) {
                        break;
                    }
                }
                if (counter.get() == cycleLimit) {
                    System.out.println("Nincs stacionárius állapot!");
                    break;
                }
                // GUI rajzolás
                controller.onCircuitUpdate();
            }

            // lefutott egy ciklus, várunk, hogy lesz-e változás
            setState(State.PAUSED);
        }

        System.out.println("Simulation is stopped!");
    }

    /**
     * Elmenti fájlba a jelforrások állapotát. 
     *
     * @return visszajelzés
     */
    public int saveSources(String fileName) {
        SourceWriter sw = new SourceWriter(fileName);
        for (IsSource source : circuit.getSources()) {
            sw.add(source);
        }
        sw.close();

        return 0;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public Object getLock() {
        return lock;
    }

    public void setState(State state) {
        currentState = state;

        switch (state) {
            case WORKING:
                // csak, ha él a szál, akkor mehet a menet.
                if (isAlive()) {
                    synchronized (synchObj) {
                        synchObj.notify();
                    }
                }
                break;
            case PAUSED:
                try {
                    synchronized (synchObj) {
                        if (interrupted()) {
                            shouldRun = false;
                        } else {
                            synchObj.wait();
                        }
                    }
                } catch (InterruptedException ex) {
                    shouldRun = false;
                }
                break;
            case STOPPED:
                System.out.println("Simulation is being stopped!");
                seqGenStepper.stopStepper();
                interrupt();
        }
    }

    Thread getStepperThread() {
        return seqGenStepper;
    }
}
