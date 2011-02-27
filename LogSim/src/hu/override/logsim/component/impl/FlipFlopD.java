package hu.override.logsim.component.impl;

import hu.override.logsim.component.FlipFlop;

/**
 * D flipflop, mely felfutó órajelnél beírja a belsõ memóriába az adatbemeneten (D)
 * lévõ értéket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    private static final int CLK = 0;
    private static final int D = 1;

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            // FIXME - órajel van, de ez így nem jó, mert felfutó ÉL KELL!!!
            currentValue[0] = evaluateInput(D);
        }
    }
}
