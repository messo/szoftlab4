package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * VAGY kapu, az �ramk�r egyik alapeleme. Bemenetein l�v� �rt�kek logikai VAGY kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
 */
public class OrGate extends AbstractComponent {

    /**
     * Konstruktor. 1 kimenete van
     * @param inputPinsCount Bemenetek sz�ma
     * @param name Vagy kapu neve
     */
    public OrGate(int inputPinsCount, String name) {
        super(name, inputPinsCount, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        for (int i = 1; i <= inputs.length; i++) {
            if (getInput(i) == Value.TRUE) {
                outputs[0].setValue(Value.TRUE);
                return;
            }
        }

        outputs[0].setValue(Value.FALSE);
    }

    @Override
    public AbstractComponent copy(String name) {
        return new OrGate(inputs.length, name);
    }
}
