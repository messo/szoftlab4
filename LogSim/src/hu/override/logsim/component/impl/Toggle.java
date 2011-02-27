package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsSource;

/**
 * Kapcsol� jelforr�s, melyet a felhaszn�l� szimul�ci� k�zben kapcsolgathat.
 *
 * @author balint
 */
public class Toggle extends AbstractComponent implements IsSource {

    @Override
    protected void onEvaluation() {
        // nop
    }

    /**
     * Kapcsol� �llapot�nak v�ltoztat�sa, csak 1 elem� t�mb�t kaphat param�ter�l.
     *
     * @param values
     */
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
            circuit.simulationShouldBeWorking();
        }
    }

    /**
     * Lek�rj�k a kapcsol� �rt�k�t (1 elem� t�mb)
     */
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = lastValue[0];
        return values;
    }

    /**
     * Kapcsol� �llapot�t megv�ltoztathatjuk. K�nyelmesebb, mint a setValues() h�v�sa
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
