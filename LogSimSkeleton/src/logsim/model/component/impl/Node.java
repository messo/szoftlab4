package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 *
 * @author Gabor
 */
public class Node extends AbstractComponent {

    public Node(int outputPinsCount, String name) {
        super(name);
        outputs = new Wire[outputPinsCount];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        Value v = inputs[0].getValue();
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].setValue(v);
        }
    }

    @Override
    public String getClassName() {
        return "Node";
    }
}
