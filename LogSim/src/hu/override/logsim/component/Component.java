package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 *
 * @author balint
 */
public interface Component {

    void setName(String name);

    String getName();

    Value getValue();

    Value getValue(int idx);
}
