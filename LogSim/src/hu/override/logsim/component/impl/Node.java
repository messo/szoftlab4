/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.Wire;

/**
 *
 * @author Gabor
 */
public class Node extends AbstractComponent {

    @Override
    protected Value[] onEvaluation() {
        for(int i=0; i<outputs.length;i++)
        {
            outputs[i] = inputs[0];
        }

    }

    public Node(int outputPinsCount)
    {
        outputs = new Wire[outputPinsCount];

    }

}
