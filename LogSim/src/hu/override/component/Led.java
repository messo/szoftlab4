package hu.override.component;

/**
 *
 * @author balint
 */
public class Led extends Component implements IsDisplay {

    @Override
    public String toString() {
        return String.format("LED(%s): %b", name, lastValue[0]);
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = inputs[0].evaluate(indices[0]);
    }
}
