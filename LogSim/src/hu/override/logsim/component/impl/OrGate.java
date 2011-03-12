package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 * VAGY kapu, az áramkör egyik alapeleme. Bemeneteire kötött komponensek
 * kiértékelését kezdeményezi, s a kapott értékek logikai VAGY kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
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
