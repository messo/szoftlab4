package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.SourceComponent;
import logsim.view.component.ToggleView;

/**
 * Kapcsoló jelforrás, melyet a felhasználó szimuláció közben kapcsolgathat.
 */
public class Toggle extends SourceComponent {

    /**
     * Kapcsoló állapota
     */
    private Value v = Value.FALSE;

    /**
     * Konstruktor
     * @param name Kapcsoló neve
     */
    public Toggle(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        // kimenet beállítása
        outputs[0].setValue(v);
    }

    @Override
    public AbstractComponent copy(String name) {
        return new Toggle(name);
    }

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     *
     * @return Érték
     */
    @Override
    public Value[] getValues() {
        Value[] values = new Value[1];
        values[0] = v;
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

        v = newValues[0];
    }

    /**
     * Kapcsoló alaphelyzetbe állítása.
     */
    @Override
    public void reset() {
        v = Value.FALSE;
    }

    @Override
    public ToggleView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
