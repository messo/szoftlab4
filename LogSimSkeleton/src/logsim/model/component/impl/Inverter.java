package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatrész, mely invertálva adja ki a kimenetén a bemenetén
 * érkezõ jelet.
 * 
 */
public class Inverter extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenet és 1 kimenet
     * @param name Inverter neve
     */
    public Inverter(String name) {
        super(name, 1, 1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // bemenetén lévõ adat lekérdezése
        evaluateInput(0);

        // vezeték új értékének bekérése
        Value v2 = Logger.logAskValue(this, "mit adjunk a vezetékre");
        outputs[0].setValue(v2);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Inverter";
    }


}
