package hu.override.logsim.component.impl;

import hu.override.logsim.component.Component;
import hu.override.logsim.Value;
/**
 *
 * @author gabooo
 */

//clk-j-k-q

//input 0     clk
//input 1     j
//input 2     k
//input 3     out

public class FlipFlopJK extends Component
{
    @Override
    protected void onEvaluation()
    {
        if ((inputs[0].evaluate(indices[1]) == Value.TRUE))
        {
            //van órajel
            int state = 0;
            if (inputs[1].evaluate(indices[1]) == Value.TRUE)
            {
                state += 2;
            }
            if (inputs[2].evaluate(indices[2]) == Value.TRUE)
            {
                state += 1;
            }

            switch (state)
            {
                case 0:
                    //itt elvileg nem változik semmit
                    break;
                case 1:
                    currentValue[0] = Value.FALSE;
                    break;
                case 2:
                    currentValue[0] = Value.TRUE;
                    break;
                case 3:
                    currentValue[0] = currentValue[0].invert();
                    break;
                default:
                    //ilyen úgyse lehet spec szerint
                    break;
            }


        }else
        {
            //nincs órajel
        }

        

    }
}
