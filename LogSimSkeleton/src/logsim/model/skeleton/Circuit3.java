package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit3 extends Circuit {

    @Override
    public void init() {
        Logger.logCall(this, "init");

        Wire wire1 = new Wire("toggle1_to_orgate");

        Toggle toggle1 = new Toggle("toggle1");
        toggle1.setOutput(0, wire1);

        Wire wire2 = new Wire("toggle2_to_orgate");

        Toggle toggle2 = new Toggle("toggle2");
        toggle2.setOutput(0, wire2);

        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, wire1);
        orgate.setInput(1, wire2);

        Wire wire3 = new Wire("orgate_to_led");

        orgate.setOutput(0, wire3);

        Led led = new Led("led");
        led.setInput(0, wire3);

        toggle1.addTo(this);
        toggle2.addTo(this);
        orgate.addTo(this);
        led.addTo(this);

        Logger.logReturn();
    }
}
