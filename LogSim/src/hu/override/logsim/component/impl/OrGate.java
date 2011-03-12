package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * VAGY kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai VAGY kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
 *
 * @author balint
 */
public class OrGate extends AbstractComponent {

    public OrGate(int inputPinsCount) {
        inputs = new Wire[inputPinsCount];
        outputs = new Wire[1];
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.FALSE);
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.TRUE) {
                outputs[0].setValue(Value.TRUE);
                System.out.println("      CALL outputs[0].setValue( [" + Value.TRUE + "] )");
                System.out.println("      RETURN");
                return;
            }
        }
        System.out.println("      CALL outputs[0].setValue( [" + Value.FALSE + "] )");
        System.out.println("      RETURN");
    }
}
