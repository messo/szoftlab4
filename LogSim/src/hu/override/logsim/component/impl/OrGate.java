package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * Vagy kaput reprezentál, melynek akkor van igaz érték a kimenetén, ha legalább
 * egy bemenetén van igaz érték.
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
