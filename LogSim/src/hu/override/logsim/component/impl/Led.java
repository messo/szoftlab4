package hu.override.logsim.component.impl;

import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsDisplay;

/**
 *
 * @author balint
 */
public class Led extends Component implements IsDisplay {

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, lastValue[0]);
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = inputs[0].evaluate(indices[0]);
    }
}
