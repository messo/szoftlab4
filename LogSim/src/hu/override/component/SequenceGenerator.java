package hu.override.component;

/**
 *
 * @author balint
 */
public class SequenceGenerator extends Component implements IsSource {

    boolean[] sequence;
    private int idx;

    public void step() {
        idx = (idx + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = sequence[idx];
    }

    public void setSequence(boolean[] sequence) {
        this.sequence = sequence;
    }
}
