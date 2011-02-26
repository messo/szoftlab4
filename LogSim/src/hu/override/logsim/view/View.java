package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.component.Component;

/**
 *
 * @author balint
 */
public interface View {

    void update(Circuit circuit);

    void addSource(Component source);

    void addDisplay(Component display);

    void layoutDone();
}
