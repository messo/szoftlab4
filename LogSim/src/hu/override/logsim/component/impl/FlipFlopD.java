package hu.override.logsim.component.impl;

import hu.override.logsim.component.FlipFlop;

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

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            // bemenetén lévõ értéket beírjuk.
            currentValue[0] = evaluateInput(D);
        }
    }
}
