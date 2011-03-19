package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.SourceComponent;

/**
 * Kapcsoló jelforrás, melyet a felhasználó szimuláció közben kapcsolgathat.
 */
public class Toggle extends SourceComponent {

    /**
     * Konstruktor
     * @param name Kapcsoló neve
     */
    public Toggle(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // állapotának lekérdezése
        Value v = Logger.logAskValue(this, "állapot");
        // kimenet beállítása
        outputs[0].setValue(v);
    }

    /**
     * Lekérjük a kapcsoló értékét (1 elemû tömb)
     *
     * @return Érték
     */
    @Override
    public Value[] getValues() {
        Logger.logCall(this, "getValues");
        // mivel nem tároljuk az értékeket, ezért ha lekérdezzük õket
        // be kell kérni õket
        Value[] values = new Value[1];
        values[0] = Logger.logAskValue(this, "érték");

        Logger.logReturn("values");
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

        // elmentjük az értéket
        outputs[0].setValue(newValues[0]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Toggle";
    }
}
