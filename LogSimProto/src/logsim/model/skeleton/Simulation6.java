package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Composite;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * 6. tesztesethez tartozó szimuláció
 */
public class Simulation6 extends Simulation {

    public Simulation6() {
        Logger.logCreate(this);
        Circuit c = new Circuit6();
        // inicializáljuk az áramkört
        c.init();
        // beregisztráljuk a szimulációnál
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsoló és egy led van összekötve.
     */
    private static class Circuit6 extends Circuit {

        @Override
        public void init() {
            // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
            Logger.logCall(this, "init");

            Wire w1 = new Wire("t1_or4");
            Wire w2 = new Wire("t2_or4");
            Wire w3 = new Wire("t3_or4");
            Wire w4 = new Wire("t4_or4");

            Wire out = new Wire("or4_led");

            Toggle t1 = new Toggle("t1");
            t1.setOutput(0, w1);
            Toggle t2 = new Toggle("t2");
            t2.setOutput(0, w2);
            Toggle t3 = new Toggle("t3");
            t3.setOutput(0, w3);
            Toggle t4 = new Toggle("t4");
            t4.setOutput(0, w4);

            Composite c1 = new Composite("OR4", 4, 1, new int[] {1, 1, 1, 1});
            c1.setInput(0, w1);
            c1.setInput(1, w2);
            c1.setInput(2, w3);
            c1.setInput(3, w4);
            c1.setOutput(0, out);

            // COMPOSITE feltöltése
            // Bemeneti interfészre csatlakozás
            Wire c1_o1_1 = new Wire("c1_o1_1");
            Wire c1_o1_2 = new Wire("c1_o1_2");
            c1.getInputNode(0).setOutput(0, c1_o1_1);
            c1.getInputNode(1).setOutput(0, c1_o1_2);
            Wire c1_o2_1 = new Wire("c1_o2_1");
            Wire c1_o2_2 = new Wire("c1_o2_2");
            c1.getInputNode(2).setOutput(0, c1_o2_1);
            c1.getInputNode(3).setOutput(0, c1_o2_2);

            // belsõ logika
            Wire o1_o3 = new Wire("o1_o3");
            Wire o2_o3 = new Wire("o2_o3");

            OrGate o1 = new OrGate(2, "or4_or1");
            o1.setInput(0, c1_o1_1);
            o1.setInput(1, c1_o1_2);
            o1.setOutput(0, o1_o3);

            OrGate o2 = new OrGate(2, "or4_or2");
            o2.setInput(0, c1_o2_1);
            o2.setInput(1, c1_o2_2);
            o2.setOutput(0, o2_o3);

            Wire o3_c1 = new Wire("o3_c1");
            OrGate o3 = new OrGate(2, "or4_or3");
            o3.setInput(0, o1_o3);
            o3.setInput(1, o2_o3);
            o3.setOutput(0, o3_c1);

            o1.addTo(c1);
            o2.addTo(c1);
            o3.addTo(c1);

            // kimeneti interfészre csatlakozás
            c1.getOutputNode(0).setInput(0, o3_c1);
            // Composite feltöltése kész.

            Led led = new Led("led");
            led.setInput(0, out);

            t1.addTo(this);
            t2.addTo(this);
            t3.addTo(this);
            t4.addTo(this);

            c1.addTo(this);

            led.addTo(this);

            // utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
            Logger.logReturn();
        }
    }
}
