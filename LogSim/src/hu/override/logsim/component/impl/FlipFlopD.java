package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.FlipFlop;

/**
 * D flipflop, mely felfut� �rajeln�l be�rja a bels� mem�ri�ba az adatbemeneten (D)
 * l�v� �rt�ket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    /**
     * D bemenet l�b�nak a sz�ma.
     */
    private static final int D = 1;

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        if (isActive()) {
            // bemenet�n l�v� �rt�ket be�rjuk.
            result[0] = evaluateInput(D);
        } else {
            // marad a r�gi.
            result[0] = q;
        }

        return result;
    }
}
