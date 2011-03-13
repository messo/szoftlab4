package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * �S kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai �S kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
 *
 * @author balint
 */
public class AndGate extends AbstractComponent {

    public AndGate(int inputPinsCount, String name) {
        super(name);
        inputs = new Wire[inputPinsCount];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(Value.TRUE);
        for (int i = 0; i < inputs.length; i++) {
            if (evaluateInput(i) == Value.FALSE) {
                outputs[0].setValue(Value.FALSE);
                System.out.println("      CALL outputs[0].setValue( [" + Value.FALSE + "] )");
                System.out.println("      RETURN");
                return;
            }
        }
        System.out.println("      CALL outputs[0].setValue( [" + Value.TRUE + "] )");
        System.out.println("      RETURN");
    }
}
