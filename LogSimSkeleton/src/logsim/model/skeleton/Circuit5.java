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
        Logger.logCall(this, "init");

        Wire toggle_to_orgate = new Wire("toggle_to_orgate");

        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, toggle_to_orgate);

        Wire orgate_to_node = new Wire("orgate_to_node");

        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, toggle_to_orgate);
        orgate.setOutput(0, orgate_to_node);

        Node n = new Node(2, "node");
        n.setInput(0, orgate_to_node);

        Wire node_to_orgate = new Wire("node_to_orgate");
        n.setOutput(0, node_to_orgate);
        orgate.setInput(1, node_to_orgate);

        Wire node_to_led = new Wire("node_to_led");
        n.setOutput(1, node_to_led);

        Led l = new Led("led");
        l.setInput(0, node_to_led);

        toggle.addTo(this);
        orgate.addTo(this);
        n.addTo(this);
        l.addTo(this);
        
        Logger.logReturn();
    }
}
