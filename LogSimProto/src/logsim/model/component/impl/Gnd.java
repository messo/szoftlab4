package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * A "f�ld" komponens, mely �lland�an a hamis �rt�ket adja ki. Nincs bemenete.
 *
 * @author balint
 */
public class Gnd extends AbstractComponent {

    public Gnd(String name) {
        super(name, 0, 1);
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.FALSE);
    }

    @Override
    public AbstractComponent copy(String newName) {
        return new Gnd(newName);
    }

    @Override
    public String getClassName() {
        return "Gnd";
    }
}
