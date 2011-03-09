package hu.override.logsim.component.impl;

import hu.override.logsim.component.DisplayComponent;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 * 3 féle színe lehet, ezeket a Color enumeráció határozza meg.
 *
 * @author balint
 */
public class Led extends DisplayComponent {

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, inputs[0].getValue());
    }

    @Override
    protected void onEvaluation() {
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }
}
