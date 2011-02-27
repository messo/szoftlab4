package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * A "f�ld" komponens, mely �lland�an a hamis �rt�ket adja ki.
 *
 * @author balint
 */
public class Gnd extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        currentValue[0] = Value.FALSE;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
