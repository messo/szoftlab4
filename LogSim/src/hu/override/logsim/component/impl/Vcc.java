package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * A t�pfesz�lt�s komponens, ami konstans igaz �rt�ket ad. Nincs bemenete.
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
