package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 *
 * @author balint
 */
public interface IsSource extends Component {

    /**
     * Beállítjuk a jelforrás értékét.
     * 
     * @param values
     */
    void setValues(Value[] values);

    Value[] getValues();
}
