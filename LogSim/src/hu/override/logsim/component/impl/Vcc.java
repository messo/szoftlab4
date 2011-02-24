package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.Component;

/**
 *
 * @author balint
 */
public class Vcc extends Component {

    @Override
    protected void onEvaluation() {
        currentValue[0] = Value.TRUE;
    }
}
