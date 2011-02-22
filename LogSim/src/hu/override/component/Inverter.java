package hu.override.component;

/**
 *
 * @author balint
 */
public class Inverter extends Component {

    @Override
    protected void onEvaluation() {
        currentValue[0] = !inputs[0].evaluate(indices[0]);
    }
}
