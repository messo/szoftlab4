package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 *
 * @author Gabor
 */
public class Node extends AbstractComponent {

    private int outputCounter = 0;
    @Override
    protected void onEvaluation() {
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].setValue(inputs[0].getValue());
        }
    }

    public Node(int outputPinsCount) {
        inputs = new Wire[1];
        outputs = new Wire[outputPinsCount];

    }

    public void setInput(Wire wire){
        inputs[0] = wire;
    }

    public void addOutput(Wire wire){
        if(outputCounter<outputs.length)
        {
            outputs[outputCounter] = wire;
            outputCounter++;
        }
        else{
            System.out.println("minden kimenetre már kötöttünk cuccot");
        }
    }
}
