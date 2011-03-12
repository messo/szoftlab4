package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 *
 * @author Gabor
 */
public class Node extends AbstractComponent {

    public Node(int outputPinsCount) {
        inputs = new Wire[1];
        outputs = new Wire[outputPinsCount];
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
