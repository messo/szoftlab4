package logsim;

import java.awt.Point;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;
import logsim.view.component.WireView;
import logsim.view.component.impl.AndGateView;
import logsim.view.component.impl.InverterView;
import logsim.view.component.impl.LedView;
import logsim.view.component.impl.ToggleView;

/**
 *
 */
public interface ComponentViewCreator {

    AndGateView createView(AndGate ag);

    WireView createView(Wire wire, Point start, Point end);

    LedView createView(Led led);

    ToggleView createView(Toggle toggle);

    InverterView createView(Inverter inv);
}
