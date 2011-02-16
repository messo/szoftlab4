package hu.override.component;

/**
 *
 * @author balint
 */
public abstract class Component {

    protected boolean lastValue;
    protected Boolean currentValue;
    
    /**
     * Megadjuk az egységnek az argumentumokat (bemenetek)
     * 
     * @param arguments 
     */
    public abstract void init(String[] arguments);
}
