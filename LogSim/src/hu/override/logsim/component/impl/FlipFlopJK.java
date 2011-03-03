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
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        if (isActive()) {
            Value j = evaluateInput(J);
            Value k = evaluateInput(K);

            if (j == Value.TRUE && k == Value.TRUE) {
                // invertálunk
                result[0] = q.invert();
            } else if (j == Value.TRUE && k == Value.FALSE) {
                // beír
                result[0] = Value.TRUE;
            } else if (j == Value.FALSE && k == Value.TRUE) {
                // töröl
                result[0] = Value.FALSE;
            } else {
                result[0] = values[0];
            }
        } else {
            result[0] = values[0];
        }

        return result;
    }
}
