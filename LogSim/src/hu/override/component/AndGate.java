package hu.override.component;

/**
 *
 * @author balint
 */
public class AndGate extends Component {

    @Override
    protected void onEvaluation() {
        for (int i = 0; i < inputs.length; i++) {
            if (!inputs[i].evaluate(indices[i])) {
                currentValue[0] = false;
                return;
            }
        }

        currentValue[0] = true;
    }
}
