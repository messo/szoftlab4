package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.Logger;
import logsim.model.Value;

/**
 * Vezet�k oszt�ly. Egy bemenettel �s egy kimenettel rendelkezik.
 *
 */
public class Wire implements Loggable {

    /**
     * Vezet�k neve
     */
    private String name;

    /**
     * Konstruktor
     * @param name vezet�k neve
     */
    public Wire(String name) {
        this.name = name;
        Logger.logCreate(this);
        Logger.logReturn();
    }

    /**
     * Vezet�k �rt�k�nek be�ll�t�sa
     * @param value �rt�k
     */
    public void setValue(Value value) {
        Logger.logCall(this, "setValue", value);
        Logger.logReturn();
    }

    /**
     * Vezet�k �rt�k�nek lek�r�se
     * @return Vezet�k �rt�ke
     */
    public Value getValue() {
        Logger.logCall(this, "getValue");
        Value v = Logger.logAskValue(this, "vezet�ken l�v� �rt�k");
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
