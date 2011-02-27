package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 *
 * @author balint
 */
public class AndGate extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.FALSE) {
                currentValue[0] = Value.FALSE;
                return;
            }
        }

        currentValue[0] = Value.TRUE;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount > 0;
    }
}
