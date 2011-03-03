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

    protected Value[] onEvaluation() {
        return values;
    }

    /**
     * Kapcsol� �llapot�nak v�ltoztat�sa, csak 1 elem� t�mb�t kaphat param�ter�l.
     *
     * @param newValues
     */
    @Override
    public void setValues(Value[] newValues) {
        if (newValues.length != 1) {
            throw new IllegalArgumentException("Kapcsol�nak csak egy �rt�ke lehet!");
        }

        // ha �j �rt�ket kapott
        if (newValues[0] != values[0]) {
            // m�g nincs ki�rt�kelve
            alreadyEvaluated = false;
            // elmentj�k az �rt�ket
            values[0] = newValues[0];
        }
    }

    /**
     * Lek�rj�k a kapcsol� �rt�k�t (1 elem� t�mb)
     */
    public Value[] getValues() {
        return values;
    }

    /**
     * Kapcsol� �llapot�t megv�ltoztathatjuk. K�nyelmesebb, mint a setValues() h�v�sa
     */
    public void toggle() {
        values[0] = values[0].invert();
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }
}
