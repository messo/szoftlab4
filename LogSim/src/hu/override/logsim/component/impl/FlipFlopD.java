package hu.override.logsim.component.impl;

import hu.override.logsim.component.Component;
import hu.override.logsim.Value;
/**
 *
 * @author gabooo
 */

//clk-d-q

//input 0     clk
//input 1     d
//input 2     out
public class FlipFlopD extends Component
{
    @Override
    protected void onEvaluation()
    {
        if (inputs[0].evaluate(indices[0]) == Value.TRUE)
        {
            //órajel van
            currentValue[0] = inputs[1].evaluate(indices[1]);
        }


    }
}
