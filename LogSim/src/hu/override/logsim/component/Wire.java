/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hu.override.logsim.component;
import hu.override.logsim.Value;
/**
 *
 * @author Gabor
 */
public class Wire {
    Value value;
    
    public void setValue(Value value)
    {
        this.value = value;
    }

    public Value getValue()
    {
        return value;
    }

}
