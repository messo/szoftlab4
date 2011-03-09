package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 *
 * @author Gabor
 */
public class Wire {

    private Value value = Value.FALSE;

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
}
