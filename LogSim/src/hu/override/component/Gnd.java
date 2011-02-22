package hu.override.component;

import hu.override.Circuit;

/**
 *
 * @author balint
 */
public class Gnd extends Component {

    @Override
    public String getName() {
        return "FALSE";
    }

    @Override
    public boolean getImmediateValue(int idx) {
        return false;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = false;
    }
}
