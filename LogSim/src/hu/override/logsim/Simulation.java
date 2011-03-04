package hu.override.logsim;

import hu.override.logsim.component.FlipFlop;
import hu.override.logsim.controller.Controller;
import hu.override.logsim.exception.CircuitAlreadyExistsException;
import hu.override.logsim.exception.InvalidCircuitDefinitionException;
import hu.override.logsim.parser.Parser;
import java.io.File;

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
public class Simulation {
    public void loadCircuitFromFile(String fileName)
            throws CircuitAlreadyExistsException, InvalidCircuitDefinitionException {
        circuit = new Parser().parse(new File(fileName));
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
         * A szimul�ci� le�llt, mert az �ramk�rnek nincs stacion�rius �llapota.
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
    private static final int cycleLimit = 100;
    /**
     * Szimul�lt �ramk�r
     */
    private Circuit circuit;
    /**
     * ciklussz�ml�l�, amely ha el�ri a 100-at, akkor le�ll a szimul�ci� �s
     * jelezz�k a felhaszn�l�nak.
     */
    private int counter;
    private final Controller controller;

    public Simulation(Controller controller) {
        this.controller = controller;
    }

    /**
     * Egy adott bemeneti kombin�ci�kra szimul�lja a h�l�zatot, am�g be nem �ll a
     * stacion�rius �llapot.
     */
    public void start() {
        // amikor elindul a szimul�ci�, akkor a steppert is ind�tsuk el.
        state = State.WORKING;
        counter = 0;
        while (counter < cycleLimit) {
            circuit.doEvaluationCycle();
            if (!circuit.isChanged()) {
                break;
            }
        }
        if (counter == cycleLimit) {
            state = State.FAILED;
            System.out.println("Nincs stacion�rius �llapot!");
            return;
        }
        circuit.commitFlipFlops();
        circuit.stepGenerators();
        // GUI rajzol�s
        controller.onCircuitUpdate();

        state = State.READY;
        System.out.println("Simulation is done!");
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
        this.circuit = circuit;
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
