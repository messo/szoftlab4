package logsim.model.component;

import logsim.model.Value;

/**
 * Vezeték osztály. Két komponens-lábat köt össze. A rajta lévõ érték lekérdezhetõ
 * és beállítható.
 */
public class Wire {

    /**
     * Vezetéken lévõ érték
     */
    private Value value = Value.FALSE;

    /**
     * Vezeték értékének beállítása
     * @param value Érték
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Vezeték értékének lekérése
     * @return Vezeték értéke
     */
    public Value getValue() {
        return value;
    }
}
