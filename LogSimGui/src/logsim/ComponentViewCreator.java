package logsim;

import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;
import logsim.view.WireView;
import logsim.view.component.AndGateView;
import logsim.view.component.InverterView;
import logsim.view.component.LedView;
import logsim.view.component.ToggleView;

/**
 *
 */
public interface ComponentViewCreator {

    AndGateView createView(AndGate ag);

    WireView createView(Wire wire);

    LedView createView(Led led);

    ToggleView createView(Toggle toggle);

    InverterView createView(Inverter inv);
}
