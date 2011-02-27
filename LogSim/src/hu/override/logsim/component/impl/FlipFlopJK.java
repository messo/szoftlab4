package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.FlipFlop;

/**
 * JK flipflop, mely a belsõ memóriáját a Követelmények résznél leírt módon
 * a J és K bemenetektõl függõen változtatja.
 *
 * @author gabooo
 */
public class FlipFlopJK extends FlipFlop {

    /**
     * J bemenet lábának a száma
     */
    private static final int J = 1;
    /**
     * K bemenet lábának a száma
     */
    private static final int K = 2;

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            Value j = evaluateInput(J);
            Value k = evaluateInput(K);

            if (j == Value.TRUE && k == Value.TRUE) {
                // invertálunk
                currentValue[0] = currentValue[0].invert();
            } else if (j == Value.TRUE && k == Value.FALSE) {
                // beír
                currentValue[0] = Value.TRUE;
            } else if (j == Value.FALSE && k == Value.TRUE) {
                // töröl
                currentValue[0] = Value.FALSE;
            } else {
                // marad.
            }
        }
    }
}
