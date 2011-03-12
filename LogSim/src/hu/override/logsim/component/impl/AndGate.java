package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * �S kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai �S kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
 *
 * @author balint
 */
public class AndGate extends AbstractComponent {

    public AndGate(int inputPinsCount) {
        inputs = new Wire[inputPinsCount];
        outputs = new Wire[1];
    }


    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.FALSE) {
                outputs[0].setValue(Value.FALSE);
                return;
            }
        }
    }
}
