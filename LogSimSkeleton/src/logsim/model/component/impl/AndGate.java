package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * ÉS kapu, az áramkör egyik alapeleme. Bemeneteire kötött komponensek
 * kiértékelését kezdeményezi, s a kapott értékek logikai ÉS kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
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
