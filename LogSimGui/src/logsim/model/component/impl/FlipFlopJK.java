package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.FlipFlop;
import logsim.view.component.ComponentView;

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
    private static final int J = 2;
    /**
     * K bemenet lábának a száma
     */
    private static final int K = 3;

    public FlipFlopJK(String name) {
        super(name, 3);
    }

    /**
     * Flipflop logika véglegesítésnél
     */
    @Override
    protected void onCommit() {
        if (isActive()) {
            Value j = getInput(J);
            Value k = getInput(K);

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

    @Override
    public FlipFlopJK copy(String newName) {
        return new FlipFlopJK(newName);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
