package hu.override.component;

import hu.override.Value;

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
