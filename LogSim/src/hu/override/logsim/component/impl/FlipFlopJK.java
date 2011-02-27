package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.FlipFlop;

/**
 * JK flipflop, mely a bels� mem�ri�j�t a K�vetelm�nyek r�szn�l le�rt m�don
 * a J �s K bemenetekt�l f�gg�en v�ltoztatja.
 *
 * @author gabooo
 */
public class FlipFlopJK extends FlipFlop {

    /**
     * J bemenet l�b�nak a sz�ma
     */
    private static final int J = 1;
    /**
     * K bemenet l�b�nak a sz�ma
     */
    private static final int K = 2;

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            Value j = evaluateInput(J);
            Value k = evaluateInput(K);

            if (j == Value.TRUE && k == Value.TRUE) {
                // invert�lunk
                currentValue[0] = currentValue[0].invert();
            } else if (j == Value.TRUE && k == Value.FALSE) {
                // be�r
                currentValue[0] = Value.TRUE;
            } else if (j == Value.FALSE && k == Value.TRUE) {
                // t�r�l
                currentValue[0] = Value.FALSE;
            } else {
                // marad.
            }
        }
    }
}
