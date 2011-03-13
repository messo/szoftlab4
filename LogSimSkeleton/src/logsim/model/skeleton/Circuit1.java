package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit1 extends Circuit {

    @Override
    public void init() {
        Logger.logCall(this, "init");
        Wire wire = new Wire("wire");

        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, wire);

        Led led = new Led("led");
        led.setInput(0, wire);

        toggle.addTo(this);
        led.addTo(this);

        Logger.logReturn();
    }
}
