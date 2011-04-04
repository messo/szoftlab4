package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * A tápfeszültés komponens, ami konstans igaz értéket ad. Nincs bemenete.
 */
public class Vcc extends AbstractComponent {

    public Vcc(String name) {
        super(name, 0, 1);
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
    }

    @Override
    public AbstractComponent copy(String newName) {
        return new Vcc(newName);
    }
}
