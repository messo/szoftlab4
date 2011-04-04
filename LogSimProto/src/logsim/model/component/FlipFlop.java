package logsim.model.component;

import logsim.model.Value;

/**
 * Flipflopok �soszt�lya, minden flipflop 0. bemenete az �rajel!
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    /**
     * Fixen az 1. bemenet az �rajel
     */
    protected static final int CLK = 1;
    /**
     * Bels� mem�ri�ja, ami a kimenet�n megjelenik, �rajel felfut� �l�n�l v�ltozhat az �llapota.
     */
    protected Value q = Value.FALSE;
    /**
     * El�z� �rv�nyes �rajel, ett�l �s a ki�rt�kel�s pillanat�ban l�v� �rajel �rt�k�t�l
     * f�gg�en �szlelhetj�k, hogy felfut� �l van-e vagy sem.
     */
    protected Value clk = Value.FALSE;

    public FlipFlop(String name, int inputCount) {
        super(name, inputCount, 1);
    }

    /**
     * Sz�molhat-e az FF? Ezt h�vja meg az FF-ek onEvaluation() met�dusa, miel�tt
     * b�rmit is csin�ln�nak.
     *
     * @return enged�lyezett-e
     */
    public boolean isActive() {
        return clk == Value.FALSE && getInput(CLK) == Value.TRUE;
    }

    /**
     * V�gleges�t�s
     */
    public void commit() {
        q = outputs[0].getValue();
        clk = getInput(CLK);
    }

    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }
}
