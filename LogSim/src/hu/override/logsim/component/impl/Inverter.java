package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        // bemenet�n l�v� �rt�ket invert�lja.
        result[0] = evaluateInput(0).invert();

        return result;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }
}
