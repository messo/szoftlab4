package logsim.model.component.impl;

import logsim.log.Logger;
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
        System.out.println("      CALL inputs[0].getValue()");
        System.out.println("      RETURN [" + inputs[0].getValue() + "]");
        System.out.println("      # Ezt replikáljuk");
        for (int i = 0; i < outputs.length; i++) {
            System.out.println("      CALL outputs[0].setValue( [" + inputs[0].getValue() + "] )");
            System.out.println("      RETURN");
            outputs[i].setValue(inputs[0].getValue());
        }
    }
}
