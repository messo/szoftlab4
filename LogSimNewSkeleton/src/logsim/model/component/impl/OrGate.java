package logsim.model.component.impl;

import logsim.log.Logger;
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
        // bemenetek lek�rdez�se
        for (int i = 0; i < inputs.length; i++) {
            getInput(i);
        }

        // kimenet �rt�k�nek bek�r�se
        Value v = Logger.logAskValue(this, "mit adjunk a vezet�kre");

        // kimenet be�ll�t�sa
        outputs[0].setValue(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "OrGate";
    }
}
