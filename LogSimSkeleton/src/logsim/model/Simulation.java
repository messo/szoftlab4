package logsim.model;

import logsim.log.Loggable;
import logsim.log.Logger;

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
     * Szimul�ci� �llapotait �rja le
     */
    public static enum State {

        /**
         * Szimul�ci� �ppen dolgozik, egy konkr�t jelforr�s-kombin�ci�t alkalmazva dolgoztatja az �ramk�rt
         */
        WORKING,
        /**
         * A szimul�ci� k�sz a fut�sra. Ilyenkor h�vhat� rajta a start() met�dus
         */
        READY,
        /**
         * A szimul�ci� le�llt, mert az �ramk�rnek nincs stacion�rius �llapota. A start() met�dus
         * �jra h�vhat� (ha a bemenetek nem v�ltoznak, tov�bbra is le fog �llni).
         */
        FAILED
    }
    /**
     * Szimul�ci� jelenlegi �llapota
     */
    private State state = State.READY;
    /**
     * cikluslimit
     */
    private static final int cycleLimit = 3;
    /**
     * Szimul�lt �ramk�r
     */
    protected Circuit circuit;

    public Simulation() {
        Logger.logComment("Inicializ�l�s");
    }

    /**
     * Egy adott bemeneti kombin�ci�kra szimul�lja a h�l�zatot, am�g be nem �ll a
     * stacion�rius �llapot.
     */
    public boolean start() {
        Logger.logCall(this, "start");
        // amikor elindul a szimul�ci�, akkor a steppert is ind�tsuk el.
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
        // GUI rajzol�s
        //controller.onCircuitUpdate();

        state = State.READY;
        //System.out.println("Simulation is done!");
        Logger.logReturn("true");
        return true;
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
     * Szimul�lt �ramk�r be�ll�t�sa
     * 
     * @param circuit
     */
    public void setCircuit(Circuit circuit) {
        Logger.logCall(this, "setCircuit", circuit);
        this.circuit = circuit;
        Logger.logReturn();
    }

    /**
     * Szimul�ci� �llapot�nak lek�rdez�se.
     *
     * @return �llapot
     */
    public State getState() {
        return state;
    }
}
