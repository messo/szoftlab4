package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

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
     * Bels� mem�ri�ja, ami a kimenet�n megjelenik, �rajel felfut� �l�n�l v�ltozhat az �llapota.
     */
    protected Value q = Value.FALSE;
    /**
     * El�z� �rv�nyes �rajel, ett�l �s a ki�rt�kel�s pillanat�ban l�v� �rajel �rt�k�t�l
     * f�gg�en �szlelhetj�k, hogy felfut� �l van-e vagy sem.
     */
    protected Value clk = Value.FALSE;

    /**
     * Sz�molhat-e az FF? Ezt h�vja meg az FF-ek onEvaluation() met�dusa, miel�tt
     * b�rmit is csin�ln�nak.
     *
     * @return enged�lyezett-e
     */
    public boolean isActive() {
        return clk == Value.FALSE && evaluateInput(CLK) == Value.TRUE;
    }

    public void commit() {
        q = values[0];
        clk = evaluateInput(CLK);
    }

    @Override
    public void addTo(Circuit circuit) {
        super.addTo(circuit);
        circuit.add(this);
    }
}
