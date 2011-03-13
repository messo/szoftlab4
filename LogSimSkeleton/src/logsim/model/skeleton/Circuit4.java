package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;

/**
 *
 * @author Balint
 */
public class Circuit4 extends Circuit {

    @Override
    public void init() {
        Logger.logCall(this, "init");

        Wire inv_to_node = new Wire("inv_to_node");
        Wire inv_to_inv = new Wire("inv_to_inv");
        Wire node_to_led = new Wire("node_to_led");

        Inverter t = new Inverter("inverter");
        t.setInput(0, inv_to_inv);
        t.setOutput(0, inv_to_node);

        Node n = new Node(2, "node");
        n.setInput(0, inv_to_node);
        n.setOutput(0, inv_to_inv);
        n.setOutput(1, node_to_led);

        Led l = new Led("led");
        l.setInput(0, node_to_led);

        t.addTo(this);
        n.addTo(this);
        l.addTo(this);
        
        Logger.logReturn();
    }
}
