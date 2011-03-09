package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * A t�pfesz�lt�s komponens, ami konstans igaz �rt�ket ad. Nincs bemenete.
 *
 * @author balint
 */
public class Vcc extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
