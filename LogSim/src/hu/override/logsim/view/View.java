package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.component.AbstractComponent;

/**
 *
 * @author balint
 */
public interface View {

    void update(Circuit circuit);

    void addSource(AbstractComponent source);

    void addDisplay(AbstractComponent display);

    void layoutDone();
}
