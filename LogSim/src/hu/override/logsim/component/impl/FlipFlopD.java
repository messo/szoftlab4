package hu.override.logsim.component.impl;

import hu.override.logsim.component.FlipFlop;

/**
 * D flipflop, mely felfut� �rajeln�l be�rja a bels� mem�ri�ba az adatbemeneten (D)
 * l�v� �rt�ket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    private static final int CLK = 0;
    private static final int D = 1;

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            // FIXME - �rajel van, de ez �gy nem j�, mert felfut� �L KELL!!!
            currentValue[0] = evaluateInput(D);
        }
    }
}
