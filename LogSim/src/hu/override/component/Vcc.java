package hu.override.component;

import hu.override.Value;

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
