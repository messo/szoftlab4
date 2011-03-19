package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;

/**
 * 4. teszteset, ahol egy inverter van visszakötve saját magába, és egy led van a kimentére kötve.
 */
public class Circuit4 extends Circuit {

    @Override
    public void init() {
        // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
        Logger.logCall(this, "init");

        // létrehozunk 3 vezetéket "inv_to_node","node_to_inv","node_to_led" névvel
        Wire inv_to_node = new Wire("inv_to_node");
        Wire node_to_inv = new Wire("node_to_inv");
        Wire node_to_led = new Wire("node_to_led");

        // létrehozunk egy invertert
        // bemenetére a node_to_inv vezetéket,
        // kimenetére az inv_to_node vezetéket kötjük
        Inverter t = new Inverter("inverter");
        t.setInput(0, node_to_inv);
        t.setOutput(0, inv_to_node);

        // létrehozunk egy csomópontot, mely 2 irányba elágazik
        // bemenetére, az inv_to_node-ot állítjuk
        // kimenetére a másik két vezetéket állítjuk, ezen a 2 vezetéken megy tovább a jel
        Node n = new Node(2, "node");
        n.setInput(0, inv_to_node);
        n.setOutput(0, node_to_inv);
        n.setOutput(1, node_to_led);

        // létrehozunk egy ledet, és bemenetére a node_to_led vezetéket kötjük
        Led l = new Led("led");
        l.setInput(0, node_to_led);

        // hozzáadjuk a hálózathoz az invertert, a csomópontot és a ledet
        t.addTo(this);
        n.addTo(this);
        l.addTo(this);

        // utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
        Logger.logReturn();
    }
}
