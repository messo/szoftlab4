package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 *
 * @author balint
 */
public class Vcc extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        currentValue[0] = Value.TRUE;
    }
}
