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
        //kimenete az ffLed1 és az inverter
        Node node = new Node(2);
        node.setInput(0, ff_to_node);
        node.setOutput(0, node_to_ffled1);
        node.setOutput(1, node_to_inv);

        //bemenete a node-tól jön kimenete invffLed1
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
    private static final int cycleLimit = 100;
    /**
     * Szimulált áramkör
     */
    private Circuit circuit;
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
            System.out.println("Nincs stacionárius állapot!");
            return;
        }
        circuit.commitFlipFlops();
        circuit.stepGenerators();
        // GUI rajzolás
        controller.onCircuitUpdate();

        state = State.READY;
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
     * Szimuláció állapotának lekérdezése.
     *
     * @return állapot
     */
    public State getState() {
        return state;
    }

    public void loadCircuitFromFile(String fileName)
            throws CircuitAlreadyExistsException, InvalidCircuitDefinitionException {
        circuit = new Parser().parse(new File(fileName));
    }
}
