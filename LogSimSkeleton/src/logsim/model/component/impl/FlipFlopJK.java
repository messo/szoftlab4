package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.FlipFlop;

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

    public FlipFlopJK(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            Value j = evaluateInput(J);
            Value k = evaluateInput(K);

            if (j == Value.TRUE && k == Value.TRUE) {
                // invert�lunk
                outputs[0].setValue(q.invert());
            } else if (j == Value.TRUE && k == Value.FALSE) {
                // be�r
                outputs[0].setValue(Value.TRUE);
            } else if (j == Value.FALSE && k == Value.TRUE) {
                // t�r�l
                outputs[0].setValue(Value.FALSE);
            } else {
                outputs[0].setValue(q);
            }
        } else {
            outputs[0].setValue(q);
        }
    }
}
