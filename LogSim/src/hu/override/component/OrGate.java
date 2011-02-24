package hu.override.component;

import hu.override.Value;

/**
 *
 * @author balint
 */
public class OrGate extends Component {

    @Override
    protected void onEvaluation() {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].evaluate(indices[i]) == Value.TRUE) {
                currentValue[0] = Value.TRUE;
                return;
            }
        }

        currentValue[0] = Value.FALSE;
    }
}
