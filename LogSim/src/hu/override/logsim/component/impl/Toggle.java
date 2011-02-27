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

    @Override
    protected void onEvaluation() {
        // nop
    }

    /**
     * Kapcsoló állapotának változtatása, csak 1 elemû tömböt kaphat paraméterül.
     *
     * @param values
     */
    @Override
    public void setValues(Value[] values) {
        if (values.length != 1) {
            throw new IllegalArgumentException("Kapcsolónak csak egy értéke lehet!");
        }

        // ha új értéket kapott
        if (values[0] != lastValue[0]) {
            // még nincs kiértékelve
            alreadyEvaluated = false;
            // elmentjük az értéket
            lastValue[0] = values[0];
            circuit.simulationShouldBeWorking();
        }
    }

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     */
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = lastValue[0];
        return values;
    }

    /**
     * Kapcsoló állapotát megváltoztathatjuk. Kényelmesebb, mint a setValues() hívása
     */
    public void toggle() {
        Value[] values = new Value[1];
        values[0] = lastValue[0].invert();
        setValues(values);
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
