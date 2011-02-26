package hu.override.logsim.component.impl;

import hu.override.logsim.component.Component;
import hu.override.logsim.Value;
/**
 *
 * @author gabooo
 */

//s1-s0-d3-d2-d1-d0-q

//input 0-1     select
//input 2-5     data in
//input 6       out
public class Mpx extends Component
{
    @Override
    protected void onEvaluation()
    {
        int selected = 0;
        if (inputs[0].evaluate(indices[0]) == Value.TRUE)
        {
            selected += 2;
        }
        if (inputs[1].evaluate(indices[0]) == Value.TRUE)
        {
            selected += 1;
        }

        switch (selected)
        {
            case 0:
                currentValue[0] = inputs[5].evaluate(indices[5]);
                break;
            case 1:
                currentValue[0] = inputs[4].evaluate(indices[4]);
                break;
            case 2:
                currentValue[0] = inputs[3].evaluate(indices[3]);
                break;
            case 3:
                currentValue[0] = inputs[2].evaluate(indices[2]);
                break;
            default:
                //ilyen úgyse lehet spec szerint
                break;
        }

    }
}
