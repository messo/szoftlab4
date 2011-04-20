package logsim.model.component.impl;

import logsim.Viewable;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.SourceComponent;

/**
 * Kapcsol� jelforr�s, melyet a felhaszn�l� szimul�ci� k�zben kapcsolgathat.
 */
public class Toggle extends SourceComponent {

    /**
     * Kapcsol� �llapota
     */
    private Value v = Value.FALSE;

    /**
     * Konstruktor
     * @param name Kapcsol� neve
     */
    public Toggle(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        // kimenet be�ll�t�sa
        outputs[0].setValue(v);
    }

    @Override
    public AbstractComponent copy(String name) {
        return new Toggle(name);
    }

    /**
     * Lek�rj�k a kapcsol� �rt�k�t (1 elem� t�mb)
     *
     * @return �rt�k
     */
    @Override
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = v;
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

        v = newValues[0];
    }

    /**
     * �rt�k ki�r�sa a kimenetre.
     * @param view
     */
    @Override
    public void writeValueTo(Viewable view) {
        view.writeToggleValue(this);
    }

    /**
     * Kapcsol� alaphelyzetbe �ll�t�sa.
     */
    @Override
    public void reset() {
        v = Value.FALSE;
    }
}
