package hu.override.logsim;

import hu.override.logsim.controller.Controller;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Egy szimulációt reprezentáló objektum.
 * Futásakor elindítja a jelgenerátor léptetõt, s utasítja az áramkört több kiértékelési
 * ciklus lefuttatásához, amíg az áramkörben van változás. Ha a változás megadott lépésen belül
 * nem áll meg, tájékoztatja a felhasználót, hogy nincs stacionárius állapot.
 * Amikor leállítódik, a jelgenerátor-léptetõt is leállítja.
 * A szál természetébõl adódóan többet nem indítható el, új szimulációhoz új példányt kell létrehozni.
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
    /**
     * Szimuláció jelenlegi állapota
     */
    private State currentState;
    /**
     * cikluslimit
     */
    private static final int cycleLimit = 100;
    /**
     * A futási ciklus változója; ha ez hamis lesz, akkor leáll a szál
     */
    private boolean shouldRun;
    /**
     * Szimulált áramkör
     */
    private Circuit circuit;
    /**
     * ciklusszámláló, amely ha eléri a 100-at, akkor leáll a szimuláció és
     * jelezzük a felhasználónak.
     */
    private AtomicInteger counter = new AtomicInteger();
    private final Controller controller;
    /**
     * segéd sync objektum, ami a szál altatásához kell.
     */
    private final Object synchObj = new Object();
    private final Object lock = new Object();
    /**
     * Jelgenerátor-léptetõ, amit elindítunk, ha elindul a szimuláció
     */
    private SequenceGeneratorStepper seqGenStepper;

    public Simulation(Circuit circuit, Controller controller) {
        super("Simulation");
        this.circuit = circuit;
        this.circuit.setSimulation(this);
        this.controller = controller;
    }

    /**
     * A szál futása közben történõ dolgokat valósítjuk meg. Lásd \ref{fig:sim_running} diagram.
     */
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
     * Szimulált áramkör lekérdezése
     *
     * @return
     */
    public Circuit getCircuit() {
        return circuit;
    }

    /**
     * Egy lock objektum, a szálak egymást kizáráshoz kell.
     *
     * @return
     */
    public Object getLock() {
        return lock;
    }

    /**
     * Állapot beállítása.
     *
     * @param state várt állapot
     */
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

    /**
     * Léptetõ-szál lekérése.
     *
     * @return
     */
    Thread getStepperThread() {
        return seqGenStepper;
    }
}
