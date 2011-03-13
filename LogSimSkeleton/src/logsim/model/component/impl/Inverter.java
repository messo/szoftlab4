package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatrész, mely invertálva adja ki a kimenetén a bemenetén
 * érkezõ jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    public Inverter(String name) {
        super(name);
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        // bemenetén lévõ értéket invertálja.
        Value v = evaluateInput(0);

        Value v2 = Logger.logAskValue(this, "mit adjunk a vezetékre");
        outputs[0].setValue(v2);
    }

    @Override
    public String getClassName() {
        return "Inverter";
    }
}
