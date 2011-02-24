package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.Component;

/**
 *
 * @author balint
 */
public class Gnd extends Component {

    @Override
    protected void onEvaluation() {
        currentValue[0] = Value.FALSE;
    }
}
