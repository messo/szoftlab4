package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 *
 * @author balint
 */
public interface IsSource extends Component {

    /**
     * Be�ll�tjuk a jelforr�s �rt�k�t.
     * 
     * @param values
     */
    void setValues(Value[] values);

    Value[] getValues();
}
