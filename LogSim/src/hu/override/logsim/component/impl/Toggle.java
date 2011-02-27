package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsSource;

/**
 *
 * @author balint
 */
public class Toggle extends AbstractComponent implements IsSource {

    @Override
    protected void onEvaluation() {
        // nop
    }

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
            circuit.simulationShouldBeRunning();
        }
    }

    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = lastValue[0];
        return values;
    }

    /**
     * Kapcsoló állapotát megváltoztatjuk
     */
    public void toggle() {
        Value[] values = new Value[1];
        values[0] = lastValue[0].invert();
        setValues(values);
    }
}
