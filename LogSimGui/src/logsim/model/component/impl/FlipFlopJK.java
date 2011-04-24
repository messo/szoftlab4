package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.FlipFlop;
import logsim.view.component.ComponentView;

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
    private static final int J = 2;
    /**
     * K bemenet l�b�nak a sz�ma
     */
    private static final int K = 3;

    public FlipFlopJK(String name) {
        super(name, 3);
    }

    /**
     * Flipflop logika v�gleges�t�sn�l
     */
    @Override
    protected void onCommit() {
        if (isActive()) {
            Value j = getInput(J);
            Value k = getInput(K);

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

    @Override
    public FlipFlopJK copy(String newName) {
        return new FlipFlopJK(newName);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
