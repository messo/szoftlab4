package logsim.model.component;

import logsim.model.Value;

/**
 * Flipflopok õsosztálya, minden flipflop 1. bemenete az órajel!
 *
 * @author balint
 */
public abstract class FlipFlop extends AbstractComponent {

    /**
     * Fixen az 1. bemenet az órajel
     */
    protected static final int CLK = 1;
    /**
     * Belsõ memóriája, ami a kimenetén megjelenik, órajel felfutó élénél változhat az állapota.
     */
    protected Value q = Value.FALSE;
    /**
     * Elõzõ érvényes órajel, ettõl és a kiértékelés pillanatában lévõ órajel értékétõl
     * függõen észlelhetjük, hogy felfutó él van-e vagy sem.
     */
    protected Value clk = Value.FALSE;

    public FlipFlop(String name, int inputCount) {
        super(name, inputCount, 1);
    }

    /**
     * Számolhat-e az FF? Ezt hívja meg az FF-ek onEvaluation() metódusa, mielõtt
     * bármit is csinálnának.
     *
     * @return engedélyezett-e
     */
    public boolean isActive() {
        return clk == Value.FALSE && getInput(CLK) == Value.TRUE;
    }

    /**
     * Véglegesítés
     */
    public void commit() {
        q = outputs[0].getValue();
        clk = getInput(CLK);
    }

    /**
     * Hozzáadás kompozithoz
     * @param composite
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }
}
