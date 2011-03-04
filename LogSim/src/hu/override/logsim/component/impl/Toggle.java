package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.SourceComponent;

/**
 * Kapcsoló jelforrás, melyet a felhasználó szimuláció közben kapcsolgathat.
 *
 * @author balint
 */
public class Toggle extends SourceComponent {

    @Override
    protected Value[] onEvaluation() {
        return values;
    }

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     */
    @Override
    public Value[] getValues() {
        return values;
    }

    /**
     * Kapcsoló állapotának változtatása, csak 1 elemû tömböt kaphat paraméterül.
     *
     * @param newValues
     */
    @Override
    public void setValues(Value[] newValues) {
        if (newValues.length != 1) {
            throw new IllegalArgumentException("Kapcsolónak csak egy értéke lehet!");
        }

        // ha új értéket kapott
        if (newValues[0] != values[0]) {
            // még nincs kiértékelve
            alreadyEvaluated = false;
            // elmentjük az értéket
            values[0] = newValues[0];
        }
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
