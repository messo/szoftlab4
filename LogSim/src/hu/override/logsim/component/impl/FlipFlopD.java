package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
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
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        if (isActive()) {
            // bemenetén lévõ értéket beírjuk.
            result[0] = evaluateInput(D);
        } else {
            // marad a régi.
            result[0] = q;
        }

        return result;
    }
}
