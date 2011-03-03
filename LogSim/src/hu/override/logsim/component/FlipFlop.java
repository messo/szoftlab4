package hu.override.logsim.component;

import hu.override.logsim.Value;
import hu.override.logsim.component.impl.SequenceGenerator;

/**
 * Flipflopok �soszt�lya, minden flipflop 0. bemenete az �rajel!
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    /**
     * Fixen a 0. bemenet az �rajel
     */
    protected static final int CLK = 0;
    /**
     * Ebben t�roljuk, hogy a FF sz�molhat-e vagy sem. (felfut� �l)
     */
    private boolean active = false;
    /**
     * Bels� mem�ri�ja, ami a kimenet�n megjelenik, �rajel felfut� �l�n�l v�ltozhat az �llapota.
     */
    protected Value q = Value.FALSE;

    /**
     * Felfut� �ln�l a SequenceGenerator-nak meg kell h�vni ezt a hozz� k�t�tt
     * FlipFlopokra, egy�b esetben t�r�lnie az active flaget. �gy tudja az FF, hogy
     * mikor kell t�nylegesen sz�molnia.
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            // ami a kimenet�n jelenleg kint van, azt most elmentj�k, mint bels� �llapot, ezzel kell
            // a tov�bbiakban sz�molni, am�g akt�v a flag.
            q = values[0];
        }
    }

    /**
     * Sz�molhat-e az FF? Ezt h�vja meg az FF-ek onEvaluation() met�dusa, miel�tt
     * b�rmit is csin�ln�nak.
     *
     * @return enged�lyezett-e
     */
    public boolean isActive() {
        return active;
    }

    /**
     * �soszt�ly implement�ci�j�nak megh�v�sa, illetve ha egy SequenceGeneratort k�t�nk
     * �ppen a CLK bemenetre, akkor az addFlipFlop() met�dus megh�v�sa rajta.
     *
     * @param inputPin
     * @param component
     * @param outputPin
     */
    @Override
    public void setInput(int inputPin, AbstractComponent component, int outputPin) {
        super.setInput(inputPin, component, outputPin);

        if (inputPin == CLK && component instanceof SequenceGenerator) {
            // CLK bemenetre egy jelgener�tort k�t�nk, ez lesz az �rajel!
            ((SequenceGenerator) component).addFlipFlop(this);
        }
    }
}
