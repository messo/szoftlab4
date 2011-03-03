package hu.override.logsim;

import hu.override.logsim.controller.Controller;
import hu.override.logsim.exception.CircuitAlreadyExistsException;
import hu.override.logsim.exception.InvalidCircuitDefinitionException;
import hu.override.logsim.parser.Parser;
import java.io.File;

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
public class Simulation {

    public void loadCircuitFromFile(String fileName)
            throws CircuitAlreadyExistsException, InvalidCircuitDefinitionException {
        circuit = new Parser().parse(new File(fileName));
    }

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
     * Szimulált áramkör
     */
    private Circuit circuit;
    /**
     * ciklusszámláló, amely ha eléri a 100-at, akkor leáll a szimuláció és
     * jelezzük a felhasználónak.
     */
    private int counter;
    private final Controller controller;

    public Simulation(Controller controller) {
        this.controller = controller;
    }

    /**
     * Egy adott bemeneti kombinációkra szimulálja a hálózatot, amíg be nem áll a
     * stacionárius állapot.
     */
    public void start() {
        // amikor elindul a szimuláció, akkor a steppert is indítsuk el.
        counter = 0;
        while (counter < cycleLimit) {
            circuit.doEvaluationCycle();
            if (!circuit.isChanged()) {
                break;
            }
        }
        if (counter == cycleLimit) {
            System.out.println("Nincs stacionárius állapot!");
            return;
        }
        circuit.stepGenerators();
        // GUI rajzolás
        controller.onCircuitUpdate();

        System.out.println("Simulation is done!");
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
     * Szimulált áramkör beállítása
     * 
     * @param circuit
     */
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    /**
     * Állapot beállítása.
     *
     * @param state várt állapot
     */
    public void setState(State state) {
        currentState = state;

        /*switch (state) {
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
        }*/
    }
}
