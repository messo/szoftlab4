package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.FlipFlop;

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

    public FlipFlopJK(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            Value j = evaluateInput(J);
            Value k = evaluateInput(K);

            if (j == Value.TRUE && k == Value.TRUE) {
                // invertálunk
                outputs[0].setValue(q.invert());
            } else if (j == Value.TRUE && k == Value.FALSE) {
                // beír
                outputs[0].setValue(Value.TRUE);
            } else if (j == Value.FALSE && k == Value.TRUE) {
                // töröl
                outputs[0].setValue(Value.FALSE);
            } else {
                outputs[0].setValue(q);
            }
        } else {
            outputs[0].setValue(q);
        }
    }
}
