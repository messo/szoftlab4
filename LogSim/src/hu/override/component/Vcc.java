package hu.override.component;

/**
 *
 * @author balint
 */
public class Vcc extends Component {

    @Override
    public String getName() {
        return "TRUE";
    }

    @Override
    public boolean getImmediateValue(int idx) {
        return true;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = true;
    }
}
