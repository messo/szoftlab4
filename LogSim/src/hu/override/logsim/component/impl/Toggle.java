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
    protected void onEvaluation() {
        System.out.println("      # jelenlegi állapot: [" + outputs[0].getValue() + "]");
    }

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     */
    @Override
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = outputs[0].getValue();
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
        if (newValues[0] != outputs[0].getValue()) {
            // elmentjük az értéket
            outputs[0].setValue(newValues[0]);
        }
    }
}
