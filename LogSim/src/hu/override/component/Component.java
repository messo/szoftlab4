package hu.override.component;

/**
 *
 * @author balint
 */
public abstract class Component {

    protected boolean lastValue;
    protected Boolean currentValue;
    private String name;

    /**
     * Megadjuk az egységnek az argumentumokat (bemenetek)
     * 
     * @param arguments 
     */
    public abstract void init(String[] arguments);

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
