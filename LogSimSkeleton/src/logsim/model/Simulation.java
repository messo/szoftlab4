package logsim.model;

import logsim.log.Loggable;
import logsim.log.Logger;

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
public class Simulation implements Loggable {

    @Override
    public String getName() {
        return "simulation";
    }

    @Override
    public String getClassName() {
        return "Simulation";
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
         * A szimuláció kész a futásra. Ilyenkor hívható rajta a start() metódus
         */
        READY,
        /**
         * A szimuláció leállt, mert az áramkörnek nincs stacionárius állapota. A start() metódus
         * újra hívható (ha a bemenetek nem változnak, továbbra is le fog állni).
         */
        FAILED
    }
    /**
     * Szimuláció jelenlegi állapota
     */
    private State state = State.READY;
    /**
     * cikluslimit
     */
    private static final int cycleLimit = 3;
    /**
     * Szimulált áramkör
     */
    protected Circuit circuit;

    public Simulation() {
        Logger.logComment("Inicializálás");
    }

    /**
     * Egy adott bemeneti kombinációkra szimulálja a hálózatot, amíg be nem áll a
     * stacionárius állapot.
     */
    public boolean start() {
        Logger.logCall(this, "start");
        // amikor elindul a szimuláció, akkor a steppert is indítsuk el.
        state = State.WORKING;
        int counter = 0;
        while (counter < cycleLimit) {
            circuit.doEvaluationCycle();
            if (!circuit.isChanged()) {
                break;
            }
            counter++;
        }
        if (counter == cycleLimit) {
            state = State.FAILED;
            Logger.logReturn("false");
            return false;
        }
        circuit.commitFlipFlops();
        circuit.stepGenerators();
        // GUI rajzolás
        //controller.onCircuitUpdate();

        state = State.READY;
        //System.out.println("Simulation is done!");
        Logger.logReturn("true");
        return true;
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
        Logger.logCall(this, "setCircuit", circuit);
        this.circuit = circuit;
        Logger.logReturn();
    }

    /**
     * Szimuláció állapotának lekérdezése.
     *
     * @return állapot
     */
    public State getState() {
        return state;
    }
}
