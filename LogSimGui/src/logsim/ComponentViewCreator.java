package logsim;

import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.view.Drawable;

/**
 *
 */
public interface ComponentViewCreator {

    Drawable createView(AndGate ag);

    Drawable createView(Wire wire);
}
