package hu.override.logsim.component.impl;

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
        currentValue[0] = evaluateInput(0).invert();
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }
}
