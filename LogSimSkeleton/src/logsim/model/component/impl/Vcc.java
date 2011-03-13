package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * A tápfeszültés komponens, ami konstans igaz értéket ad. Nincs bemenete.
 *
 * @author balint
 */
public class Vcc extends AbstractComponent {

    public Vcc(String name) {
        super(name);
        inputs = new Wire[0];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
    }
}
