package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.component.DisplayComponent;
import hu.override.logsim.component.SourceComponent;

/**
 *
 * @author balint
 */
public interface View {

    void update(Circuit circuit);

    void addSource(SourceComponent source);

    void addDisplay(DisplayComponent display);

    void layoutDone();
}
