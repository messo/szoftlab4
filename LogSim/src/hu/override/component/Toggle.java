package hu.override.component;

import hu.override.Value;

/**
 *
 * @author balint
 */
public class Toggle extends Component implements IsSource {

    @Override
    protected void onEvaluation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setValues(Value[] values) {
        if (values.length != 1) {
            throw new IllegalArgumentException();
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
