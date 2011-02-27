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
            if (inputs[i].evaluate(indices[i]) == Value.FALSE) {
                currentValue[0] = Value.FALSE;
                return;
            }
        }

        currentValue[0] = Value.TRUE;
    }
}
