package hu.override.logsim.component;

import hu.override.logsim.Value;
import hu.override.logsim.component.impl.SequenceGenerator;

/**
 * Flipflopok õsosztálya, minden flipflop 0. bemenete az órajel!
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    /**
     * Fixen a 0. bemenet az órajel
     */
    protected static final int CLK = 0;
    /**
     * Ebben tároljuk, hogy a FF számolhat-e vagy sem. (felfutó él)
     */
    private boolean active = false;
    /**
     * Belsõ memóriája, ami a kimenetén megjelenik, órajel felfutó élénél változhat az állapota.
     */
    protected Value q = Value.FALSE;

    /**
     * Felfutó élnél a SequenceGenerator-nak meg kell hívni ezt a hozzá kötött
     * FlipFlopokra, egyéb esetben törölnie az active flaget. Így tudja az FF, hogy
     * mikor kell ténylegesen számolnia.
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            // ami a kimenetén jelenleg kint van, azt most elmentjük, mint belsõ állapot, ezzel kell
            // a továbbiakban számolni, amíg aktív a flag.
            q = values[0];
        }
    }

    /**
     * Számolhat-e az FF? Ezt hívja meg az FF-ek onEvaluation() metódusa, mielõtt
     * bármit is csinálnának.
     *
     * @return engedélyezett-e
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Õsosztály implementációjának meghívása, illetve ha egy SequenceGeneratort kötünk
     * éppen a CLK bemenetre, akkor az addFlipFlop() metódus meghívása rajta.
     *
     * @param inputPin
     * @param component
     * @param outputPin
     */
    @Override
    public void setInput(int inputPin, AbstractComponent component, int outputPin) {
        super.setInput(inputPin, component, outputPin);

        if (inputPin == CLK && component instanceof SequenceGenerator) {
            // CLK bemenetre egy jelgenerátort kötünk, ez lesz az órajel!
            ((SequenceGenerator) component).addFlipFlop(this);
        }
    }
}
