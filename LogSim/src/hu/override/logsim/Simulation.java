package hu.override.logsim;

import hu.override.logsim.component.Wire;
import hu.override.logsim.component.impl.AndGate;
import hu.override.logsim.component.impl.FlipFlopJK;
import hu.override.logsim.component.impl.Inverter;
import hu.override.logsim.component.impl.Led;
import hu.override.logsim.component.impl.Node;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.component.impl.Toggle;
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

//    void loadDefault() {
//        circuit = new Circuit();
//
//        Wire x_to_ff = new Wire();
//        Wire j_to_ff = new Wire();
//        Wire k_to_ff = new Wire();
//        Wire y_to_z = new Wire();
//        Wire z_to_led = new Wire();
//        Wire ff_to_ffled = new Wire();
//
//        SequenceGenerator x = new SequenceGenerator();
//        x.setName("x");
//        x.setOutput(0, x_to_ff);
//        Toggle y = new Toggle();
//        y.setName("y");
//        y.setOutput(0, y_to_z);
//        Toggle j = new Toggle();
//        j.setName("J");
//        j.setOutput(0, j_to_ff);
//        Toggle k = new Toggle();
//        k.setName("K");
//        k.setOutput(0, k_to_ff);
//        FlipFlopJK ff = new FlipFlopJK();
//        ff.setName("ff");
//        ff.setInputPinsCount(3);
//        ff.setInput(0, x_to_ff);
//        ff.setInput(1, j_to_ff);
//        ff.setInput(2, k_to_ff);
//        ff.setOutput(0, ff_to_ffled);
//        AndGate z = new AndGate();
//        z.setName("z");
//        z.setInputPinsCount(2);
//        z.setInput(0, x_to_ff);
//        z.setInput(1, y_to_z);
//        z.setOutput(0, z_to_led);
//        Led led = new Led();
//        led.setName("led");
//        led.setInputPinsCount(1);
//        led.setInput(0, z_to_led);
//        Led ffLed = new Led();
//        ffLed.setName("ffLed");
//        ffLed.setInputPinsCount(1);
//        ffLed.setInput(0, ff_to_ffled);
//
//        x.addTo(circuit);
//        y.addTo(circuit);
//        j.addTo(circuit);
//        k.addTo(circuit);
//        ff.addTo(circuit);
//        z.addTo(circuit);
//        led.addTo(circuit);
//        ffLed.addTo(circuit);
//    }
    ///node-dal
    void loadDefault() {
        circuit = new Circuit();

        Wire x_to_ff = new Wire();
        Wire j_to_ff = new Wire();
        Wire k_to_ff = new Wire();
        Wire y_to_z = new Wire();
        Wire z_to_led = new Wire();
        Wire ff_to_node = new Wire();
        Wire node_to_ffled1 = new Wire();
        Wire node_to_inv = new Wire();
        Wire inv_to_led2 = new Wire();

        SequenceGenerator x = new SequenceGenerator();
        x.setName("x");
        x.setOutput(0, x_to_ff);
        Toggle y = new Toggle();
        y.setName("y");
        y.setOutput(0, y_to_z);
        Toggle j = new Toggle();
        j.setName("J");
        j.setOutput(0, j_to_ff);
        Toggle k = new Toggle();
        k.setName("K");
        k.setOutput(0, k_to_ff);
        FlipFlopJK ff = new FlipFlopJK();
        ff.setName("ff");
        ff.setInput(0, x_to_ff);
        ff.setInput(1, j_to_ff);
        ff.setInput(2, k_to_ff);
        //ff.setOutput(0, ff_to_ffled);
        ff.setOutput(0, ff_to_node);
        AndGate z = new AndGate(2);
        z.setName("z");
        z.setInput(0, x_to_ff);
        z.setInput(1, y_to_z);
        z.setOutput(0, z_to_led);
        Led led = new Led();
        led.setName("led");
        led.setInput(0, z_to_led);
        Led ffLed1 = new Led();
        ffLed1.setName("ffLed1");
        ffLed1.setInput(0, node_to_ffled1);
        Led ffLed2 = new Led();
        ffLed2.setName("invffLed1");
        ffLed2.setInput(0, inv_to_led2);

        //node bemente a ff
        //kimenete az ffLed1 �s az inverter
        Node node = new Node(2);
        node.setInput(0, ff_to_node);
        node.setOutput(0, node_to_ffled1);
        node.setOutput(1, node_to_inv);

        //bemenete a node-t�l j�n kimenete invffLed1
        Inverter inv = new Inverter();
        inv.setName("inverter");
        inv.setInput(0, node_to_inv);
        inv.setOutput(0, inv_to_led2);

        x.addTo(circuit);
        y.addTo(circuit);
        j.addTo(circuit);
        k.addTo(circuit);
        ff.addTo(circuit);
        z.addTo(circuit);
        led.addTo(circuit);
        ffLed1.addTo(circuit);
        ffLed2.addTo(circuit);
        node.addTo(circuit);
        inv.addTo(circuit);
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
    private static final int cycleLimit = 100;
    /**
     * Szimul�lt �ramk�r
     */
    private Circuit circuit;
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
        int counter = 0;
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

    public void loadCircuitFromFile(String fileName)
            throws CircuitAlreadyExistsException, InvalidCircuitDefinitionException {
        circuit = new Parser().parse(new File(fileName));
    }
}
