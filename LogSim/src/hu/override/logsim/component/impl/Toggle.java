package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.SourceComponent;

/**
 * Kapcsol� jelforr�s, melyet a felhaszn�l� szimul�ci� k�zben kapcsolgathat.
 *
 * @author balint
 */
public class Toggle extends SourceComponent {

    @Override
    protected void onEvaluation() {
        System.out.println("      # jelenlegi �llapot: [" + outputs[0].getValue() + "]");
    }

    /**
     * Lek�rj�k a kapcsol� �rt�k�t (1 elem� t�mb)
     */
    @Override
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = outputs[0].getValue();
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
        if (newValues[0] != outputs[0].getValue()) {
            // elmentj�k az �rt�ket
            outputs[0].setValue(newValues[0]);
        }
    }
}
