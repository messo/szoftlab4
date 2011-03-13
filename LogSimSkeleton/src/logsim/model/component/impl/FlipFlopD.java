package logsim.model.component.impl;

import logsim.model.component.FlipFlop;

/**
 * D flipflop, mely felfutó órajelnél beírja a belsõ memóriába az adatbemeneten (D)
 * lévõ értéket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    /**
     * D bemenet lábának a száma.
     */
    private static final int D = 1;

    public FlipFlopD(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            // bemenetén lévõ értéket beírjuk.
            outputs[0].setValue(evaluateInput(D));
        } else {
            // marad a régi.
            outputs[0].setValue(q);
        }
    }
}
