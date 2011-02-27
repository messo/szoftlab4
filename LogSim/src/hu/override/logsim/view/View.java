package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;

/**
 *
 * @author balint
 */
public interface View {

    void update(Circuit circuit);

    void addSource(IsSource source);

    void addDisplay(IsDisplay display);

    void layoutDone();
}
