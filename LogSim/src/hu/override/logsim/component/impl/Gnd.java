package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * A "föld" komponens, mely állandóan a hamis értéket adja ki. Nincs bemenete.
 *
 * @author balint
 */
public class Gnd extends AbstractComponent {

    public Gnd() {
        inputs = new Wire[0];
        outputs = new Wire[1];
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.FALSE);
    }
}
