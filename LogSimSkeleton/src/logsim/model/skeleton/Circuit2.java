package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit2 extends Circuit {

    @Override
    public void init() {
        Logger.logCall(this, "init");
        Wire wire1 = new Wire("toggle_to_inv");

        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, wire1);

        Inverter inv = new Inverter("inv");
        inv.setInput(0, wire1);

        Wire wire2 = new Wire("inv_to_led");

        inv.setOutput(0, wire2);

        Led led = new Led("led");
        led.setInput(0, wire2);

        toggle.addTo(this);
        inv.addTo(this);
        led.addTo(this);

        Logger.logReturn();
    }
}
