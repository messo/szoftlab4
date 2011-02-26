package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsSource;

/**
 *
 * @author balint
 */
public class Toggle extends Component implements IsSource {

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
            circuit.simulationRefreshRequired();
        }
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
