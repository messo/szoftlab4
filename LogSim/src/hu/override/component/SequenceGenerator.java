package hu.override.component;

import hu.override.Value;

/**
 *
 * @author balint
 */
public class SequenceGenerator extends Component implements IsSource {

    Value[] sequence;
    private int idx;

    public void step() {
        idx = (idx + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = sequence[idx];
    }

    public void setValues(Value[] values) {
        this.sequence = sequence;
    }
}
