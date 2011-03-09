package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        // bemenet�n l�v� �rt�ket invert�lja.
        outputs[0].setValue(evaluateInput(0).invert());
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }
}
