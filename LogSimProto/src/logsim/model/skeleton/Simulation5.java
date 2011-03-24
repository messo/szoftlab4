package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * 5. tesztesethez tartozó szimuláció
 */
public class Simulation5 extends Simulation {

    public Simulation5() {
        Logger.logCreate(this);
        Circuit c = new Circuit5();
        // inicializáljuk az áramkört
        c.init();
        // beregisztráljuk a szimulációnál
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsoló és egy led van összekötve.
     */
    private static class Circuit5 extends Circuit {

        @Override
        public void init() {
            // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
            Logger.logCall(this, "init");

            // létrehozunk egy vezetéket, ami a kapcsolóból a vagykapuba megy
            Wire toggle_to_orgate = new Wire("toggle_to_orgate");

            // létrehozzuk a kapcsolót, és kimenetére rákötjük az elõzõ vezetéket
            Toggle toggle = new Toggle("toggle");
            toggle.setOutput(0, toggle_to_orgate);

            // létrehozunk 3 vezetéket
            Wire orgate_to_node = new Wire("orgate_to_node");
            Wire node_to_orgate = new Wire("node_to_orgate");
            Wire node_to_led = new Wire("node_to_led");

            // létrehozunk egy 2 bemenetû vagykaput
            // egyik bemenetére a kapcsolóból jövõ vezetéket (toggle_to_orgate) kötjük
            // másikra a csomópontból jövõ egyik vezetéket (visszacsatolt vezeték)
            // kimenetére a csomópontba menõ vezetéket (orgate_to_node) kötjük
            OrGate orgate = new OrGate(2, "orgate");
            orgate.setInput(0, toggle_to_orgate);
            orgate.setInput(1, node_to_orgate);
            orgate.setOutput(0, orgate_to_node);

            // létrehozzuk a 2 felé ágazó csomópontot
            // bemenetére rákötjük a vagykapuból kijövõ vezetéket
            Node n = new Node(2, "node");
            n.setInput(0, orgate_to_node);
            // kimeneteire pedig a vagy kapuba menõt (node_to_orgate)
            // illetve a ledhez menõt (node_to_led) kötjük
            n.setOutput(0, node_to_orgate);
            n.setOutput(1, node_to_led);

            // létrehozunk egy ledet, aminek bemenetére a node_to_led-et kötjük
            Led l = new Led("led");
            l.setInput(0, node_to_led);

            // hozzáadjuk az áramkörhöz az elemeket
            toggle.addTo(this);
            orgate.addTo(this);
            n.addTo(this);
            l.addTo(this);

            // utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
            Logger.logReturn();
        }
    }
}
