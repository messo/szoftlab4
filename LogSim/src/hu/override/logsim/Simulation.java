package hu.override.logsim;

import hu.override.logsim.controller.Controller;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Egy szimul�ci�t reprezent�l� objektum.
 * Fut�sakor elind�tja a jelgener�tor l�ptet�t, s utas�tja az �ramk�rt t�bb ki�rt�kel�si
 * ciklus lefuttat�s�hoz, am�g az �ramk�rben van v�ltoz�s. Ha a v�ltoz�s megadott l�p�sen bel�l
 * nem �ll meg, t�j�koztatja a felhaszn�l�t, hogy nincs stacion�rius �llapot.
 * Amikor le�ll�t�dik, a jelgener�tor-l�ptet�t is le�ll�tja.
 * A sz�l term�szet�b�l ad�d�an t�bbet nem ind�that� el, �j szimul�ci�hoz �j p�ld�nyt kell l�trehozni.
 *
 * @author balint
 */
public class Simulation extends Thread {

    /**
     * Szimul�ci� �llapotait �rja le
     */
    public static enum State {

        /**
         * Szimul�ci� �ppen dolgozik, egy konkr�t jelforr�s-kombin�ci�t alkalmazva dolgoztatja az �ramk�rt
         */
        WORKING,
        /**
         * Szimul�ci� le�llt, ahhoz, hogy b�rmi t�rt�njen az �ramk�rre �jra kell ind�tani.
         */
        STOPPED,
        /**
         * Szimul�ci� sz�neteltetve van, a k�vetkez� jelforr�s v�ltoz�sig.
         */
        PAUSED
    }
    /**
     * Szimul�ci� jelenlegi �llapota
     */
    private State currentState;
    /**
     * cikluslimit
     */
    private static final int cycleLimit = 100;
    /**
     * A fut�si ciklus v�ltoz�ja; ha ez hamis lesz, akkor le�ll a sz�l
     */
    private boolean shouldRun;
    /**
     * Szimul�lt �ramk�r
     */
    private Circuit circuit;
    /**
     * ciklussz�ml�l�, amely ha el�ri a 100-at, akkor le�ll a szimul�ci� �s
     * jelezz�k a felhaszn�l�nak.
     */
    private AtomicInteger counter = new AtomicInteger();
    private final Controller controller;
    /**
     * seg�d sync objektum, ami a sz�l altat�s�hoz kell.
     */
    private final Object synchObj = new Object();
    private final Object lock = new Object();
    /**
     * Jelgener�tor-l�ptet�, amit elind�tunk, ha elindul a szimul�ci�
     */
    private SequenceGeneratorStepper seqGenStepper;

    public Simulation(Circuit circuit, Controller controller) {
        super("Simulation");
        this.circuit = circuit;
        this.circuit.setSimulation(this);
        this.controller = controller;
    }

    /**
     * A sz�l fut�sa k�zben t�rt�n� dolgokat val�s�tjuk meg. L�sd \ref{fig:sim_running} diagram.
     */
    @Override
    public void run() {
        // amikor elindul a szimul�ci�, akkor a steppert is ind�tsuk el.
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
                    System.out.println("Nincs stacion�rius �llapot!");
                    break;
                }
                // GUI rajzol�s
                controller.onCircuitUpdate();
            }

            // lefutott egy ciklus, v�runk, hogy lesz-e v�ltoz�s
            setState(State.PAUSED);
        }

        System.out.println("Simulation is stopped!");
    }

    /**
     * Szimul�lt �ramk�r lek�rdez�se
     *
     * @return
     */
    public Circuit getCircuit() {
        return circuit;
    }

    /**
     * Egy lock objektum, a sz�lak egym�st kiz�r�shoz kell.
     *
     * @return
     */
    public Object getLock() {
        return lock;
    }

    /**
     * �llapot be�ll�t�sa.
     *
     * @param state v�rt �llapot
     */
    public void setState(State state) {
        currentState = state;

        switch (state) {
            case WORKING:
                // csak, ha �l a sz�l, akkor mehet a menet.
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
     * L�ptet�-sz�l lek�r�se.
     *
     * @return
     */
    Thread getStepperThread() {
        return seqGenStepper;
    }
}
