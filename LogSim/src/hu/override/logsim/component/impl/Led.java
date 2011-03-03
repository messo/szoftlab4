package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
 * 3 f�le sz�ne lehet, ezeket a Color enumer�ci� hat�rozza meg.
 *
 * @author balint
 */
public class Led extends AbstractComponent implements IsDisplay {

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, values[0]);
    }

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        result[0] = evaluateInput(0);
        return result;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }
}
