package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * A "f�ld" komponens, mely �lland�an a hamis �rt�ket adja ki. Nincs bemenete.
 *
 * @author balint
 */
public class Gnd extends AbstractComponent {

    public Gnd(String name) {
        super(name);
        inputs = new Wire[0];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.FALSE);
    }
}
