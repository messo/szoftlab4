package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.view.component.ComponentView;

/**
 * A t�pfesz�lt�s komponens, ami konstans igaz �rt�ket ad. Nincs bemenete.
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

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
