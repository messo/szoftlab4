package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * Inverter alkatrész, mely invertálva adja ki a kimenetén a bemenetén
 * érkezõ jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        // bemenetén lévõ értéket invertálja.
        Value v = evaluateInput(0).invert();
        outputs[0].setValue(v);
        System.out.println("      CALL outputs[0].setValue( [" + v + "] )");
        System.out.println("      RETURN");
    }
}
