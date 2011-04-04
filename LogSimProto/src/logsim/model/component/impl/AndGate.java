package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * ÉS kapu, az áramkör egyik alapeleme. Bemeneteire kötött komponensek
 * kiértékelését kezdeményezi, s a kapott értékek logikai ÉS kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
 *
 * @author balint
 */
public class AndGate extends AbstractComponent {

    public AndGate(int pinsCount, String name) {
        super(name, pinsCount, 1);
    }

    @Override
    protected void onEvaluation() {
        for (int i = 1; i <= inputs.length; i++) {
            if (getInput(i) == Value.FALSE) {
                outputs[0].setValue(Value.FALSE);
                return;
            }
        }

        outputs[0].setValue(Value.TRUE);
    }

    @Override
    public AndGate copy(String newName) {
        return new AndGate(inputs.length, newName);
    }
}
