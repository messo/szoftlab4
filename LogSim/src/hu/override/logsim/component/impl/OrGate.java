package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * Vagy kaput reprezent�l, melynek akkor van igaz �rt�k a kimenet�n, ha legal�bb
 * egy bemenet�n van igaz �rt�k.
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
