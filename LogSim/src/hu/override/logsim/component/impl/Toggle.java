package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsSource;

/**
 * Kapcsoló jelforrás, melyet a felhasználó szimuláció közben kapcsolgathat.
 *
 * @author balint
 */
public class Toggle extends AbstractComponent implements IsSource {

    protected Value[] onEvaluation() {
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

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     */
    public Value[] getValues() {
        return values;
    }

    /**
     * Kapcsoló állapotát megváltoztathatjuk. Kényelmesebb, mint a setValues() hívása
     */
    public void toggle() {
        values[0] = values[0].invert();
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
