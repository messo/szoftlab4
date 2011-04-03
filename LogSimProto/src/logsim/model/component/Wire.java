package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.Logger;
import logsim.model.Value;

/**
 * Vezet�k oszt�ly. K�t komponens-l�bat k�t �ssze. A rajta l�v� �rt�k lek�rdezhet�
 * �s be�ll�that�.
 */
public class Wire implements Loggable {

    /**
     * Vezet�k neve
     */
    private String name;
    /**
     * Vezet�ken l�v� �rt�k
     */
    private Value value;

    /**
     * Konstruktor
     * @param name vezet�k neve
     */
    public Wire(String name) {
        this.name = name;
    }

    /**
     * Vezet�k �rt�k�nek be�ll�t�sa
     * @param value �rt�k
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Vezet�k �rt�k�nek lek�r�se
     * @return Vezet�k �rt�ke
     */
    public Value getValue() {
        return value;
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
