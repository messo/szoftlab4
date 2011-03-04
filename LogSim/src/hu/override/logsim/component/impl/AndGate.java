package hu.override.logsim.component.impl;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * �S kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai �S kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
 *
 * @author balint
 */
public class AndGate extends AbstractComponent {

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        result[0] = Value.TRUE;
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.FALSE) {
                result[0] = Value.FALSE;
                break;
            }
        }

        return result;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount > 0;
    }
}
