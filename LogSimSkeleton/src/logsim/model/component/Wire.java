package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.Logger;
import logsim.model.Value;

/**
 * Vezeték osztály. Egy bemenettel és egy kimenettel rendelkezik.
 *
 */
public class Wire implements Loggable {

    /**
     * Vezeték neve
     */
    private String name;

    /**
     * Konstruktor
     * @param name vezeték neve
     */
    public Wire(String name) {
        this.name = name;
        Logger.logCreate(this);
        Logger.logReturn();
    }

    /**
     * Vezeték értékének beállítása
     * @param value Érték
     */
    public void setValue(Value value) {
        Logger.logCall(this, "setValue", value);
        Logger.logReturn();
    }

    /**
     * Vezeték értékének lekérése
     * @return Vezeték értéke
     */
    public Value getValue() {
        Logger.logCall(this, "getValue");
        Value v = Logger.logAskValue(this, "vezetéken lévõ érték");
        Logger.logReturn(v.toString());
        return v;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Wire";
    }
}
