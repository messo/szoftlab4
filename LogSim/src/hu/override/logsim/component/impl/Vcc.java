package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * A tápfeszültés komponens, ami konstans igaz értéket ad. Nincs bemenete.
 *
 * @author balint
 */
public class Vcc extends AbstractComponent {

    public Vcc() {
        inputs = new Wire[0];
        outputs = new Wire[1];
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
    }
}
