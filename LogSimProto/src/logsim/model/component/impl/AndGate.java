package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * �S kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai �S kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
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
