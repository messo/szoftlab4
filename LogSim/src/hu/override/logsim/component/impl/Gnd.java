package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * A "f�ld" komponens, mely �lland�an a hamis �rt�ket adja ki. Nincs bemenete.
 *
 * @author balint
 */
public class Gnd extends AbstractComponent {

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        result[0] = Value.FALSE;

        return values;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
