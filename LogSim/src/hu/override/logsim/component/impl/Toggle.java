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
            throw new IllegalArgumentException("Kapcsol�nak csak egy �rt�ke lehet!");
        }

        // ha �j �rt�ket kapott
        if (values[0] != lastValue[0]) {
            // m�g nincs ki�rt�kelve
            alreadyEvaluated = false;
            // elmentj�k az �rt�ket
            lastValue[0] = values[0];
            circuit.simulationRefreshRequired();
        }
    }

    /**
     * Kapcsol� �llapot�t megv�ltoztatjuk
     */
    public void toggle() {
        Value[] values = new Value[1];
        values[0] = lastValue[0].invert();
        setValues(values);
    }
}
