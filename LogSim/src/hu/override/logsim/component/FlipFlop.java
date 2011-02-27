package hu.override.logsim.component;

/**
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    private boolean active = false;

    /**
     * Felfutó élnél a SequenceGenerator-nak meg kell hívni ezt a hozzá kötött
     * FlipFlopokra, egyéb esetben törölnie az active flaget. Így tudja az FF, hogy
     * mikor kell ténylegesen számolnia.
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
