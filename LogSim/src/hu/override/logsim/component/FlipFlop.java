package hu.override.logsim.component;

/**
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    private boolean active = false;

    /**
     * Felfut� �ln�l a SequenceGenerator-nak meg kell h�vni ezt a hozz� k�t�tt
     * FlipFlopokra, egy�b esetben t�r�lnie az active flaget. �gy tudja az FF, hogy
     * mikor kell t�nylegesen sz�molnia.
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
