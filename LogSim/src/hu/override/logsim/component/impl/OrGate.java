package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * VAGY kapu, az áramkör egyik alapeleme. Bemeneteire kötött komponensek
 * kiértékelését kezdeményezi, s a kapott értékek logikai VAGY kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
 *
 * @author balint
 */
public class OrGate extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.TRUE) {
                currentValue[0] = Value.TRUE;
                return;
            }
        }

        currentValue[0] = Value.FALSE;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount > 0;
    }
}
