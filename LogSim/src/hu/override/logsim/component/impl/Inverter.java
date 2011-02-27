package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;

/**
 *
 * @author balint
 */
public class Inverter extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        currentValue[0] = inputs[0].evaluate(indices[0]).invert();
    }
}
