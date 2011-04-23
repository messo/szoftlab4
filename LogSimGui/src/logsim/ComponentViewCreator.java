package logsim;

import java.awt.Point;
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.model.component.impl.Gnd;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Mpx;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;
import logsim.model.component.impl.Vcc;
import logsim.view.component.ComponentView;
import logsim.view.component.WireView;
import logsim.view.component.impl.AndGateView;
import logsim.view.component.impl.GndView;
import logsim.view.component.impl.InverterView;
import logsim.view.component.impl.LedView;
import logsim.view.component.impl.MpxView;
import logsim.view.component.impl.SevenSegmentDisplayView;
import logsim.view.component.impl.ToggleView;
import logsim.view.component.impl.VccView;

/**
 *
 */
public interface ComponentViewCreator {

    AndGateView createView(AndGate ag);

    WireView createView(Wire wire, Point start, Point end);

    LedView createView(Led led);

    ToggleView createView(Toggle toggle);

    InverterView createView(Inverter inv);

    MpxView createView(Mpx mpx);

    SevenSegmentDisplayView createView(SevenSegmentDisplay ssd);

    GndView createView(Gnd gnd);

    VccView createView(Vcc vcc);
}
