package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

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
     * Belsõ memóriája, ami a kimenetén megjelenik, órajel felfutó élénél változhat az állapota.
     */
    protected Value q = Value.FALSE;
    /**
     * Elõzõ érvényes órajel, ettõl és a kiértékelés pillanatában lévõ órajel értékétõl
     * függõen észlelhetjük, hogy felfutó él van-e vagy sem.
     */
    protected Value clk = Value.FALSE;

    /**
     * Számolhat-e az FF? Ezt hívja meg az FF-ek onEvaluation() metódusa, mielõtt
     * bármit is csinálnának.
     *
     * @return engedélyezett-e
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
