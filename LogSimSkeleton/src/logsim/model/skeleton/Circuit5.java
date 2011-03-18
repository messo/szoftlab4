package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit5 extends Circuit {

    @Override
    public void init() {

        //Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
        Logger.logCall(this, "init");
        
        //létrehozunk egy vezetéket, ami a kapcsolóból a vagykapuba megy
        Wire toggle_to_orgate = new Wire("toggle_to_orgate");

        //létrehozzuk a kapcsolót, és kimenetére rákötjük az elõzõ vezetéket
        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, toggle_to_orgate);

        //létrehozunk egy újabb vezetéket, ami a vagykapuból a csomópontba megy
        Wire orgate_to_node = new Wire("orgate_to_node");

        //létrehozunk egy 2 bemenetû vagykaput
        //egyik bemenetére a kapcsolóból jövõ vezetéket kötjük
        //kimenetére a csomópontba menõ vezetéket kötjük
        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, toggle_to_orgate);
        orgate.setOutput(0, orgate_to_node);

        //létrehozzuk a 2 felé ágazó csomópontot
        //rákötjük a vagykapuból kijövõ vezetékre
        Node n = new Node(2, "node");
        n.setInput(0, orgate_to_node);

        //létrehozunk egy visszacsatoló vezetéket
        //és beállítjuk, hogy a csomópontból visszaágazzon a vagykapuba
        //és egyúttal beállítjuk a vagykapu bemenetére is ezt a vezetéket
        Wire node_to_orgate = new Wire("node_to_orgate");
        n.setOutput(0, node_to_orgate);
        orgate.setInput(1, node_to_orgate);

        //létrehozunk egy másik vezetéket ami a csomópont másik ágából jön
        //beállítjuk a csomópont kimenetére
        Wire node_to_led = new Wire("node_to_led");
        n.setOutput(1, node_to_led);

        //létrehozunk egy ledet, aminek bemenetére az elõzõ vezetéket kötjük
        Led l = new Led("led");
        l.setInput(0, node_to_led);

        //hozzáadjuk az áramkörhöz az elemeket
        toggle.addTo(this);
        orgate.addTo(this);
        n.addTo(this);
        l.addTo(this);
        
        //utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
        Logger.logReturn();
    }
}
