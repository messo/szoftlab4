package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsSource;

/**
 *
 * @author balint
 */
public class SequenceGenerator extends Component implements IsSource {

    private Value[] sequence;
    private int idx;

    public void step() {
        idx = (idx + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = sequence[idx];
    }

    public void setValues(Value[] values) {
        this.sequence = values;
    }
}
