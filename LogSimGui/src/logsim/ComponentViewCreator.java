package logsim;

import java.awt.Point;
import logsim.model.component.Composite;
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.model.component.impl.FlipFlopD;
import logsim.model.component.impl.FlipFlopJK;
import logsim.model.component.impl.Gnd;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Mpx;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;
import logsim.model.component.impl.Vcc;
import logsim.view.component.WireView;
import logsim.view.component.impl.AndGateView;
import logsim.view.component.impl.CompositeView;
import logsim.view.component.impl.FlipFlopDView;
import logsim.view.component.impl.FlipFlopJKView;
import logsim.view.component.impl.GndView;
import logsim.view.component.impl.InverterView;
import logsim.view.component.impl.LedView;
import logsim.view.component.impl.MpxView;
import logsim.view.component.impl.NodeView;
import logsim.view.component.impl.OrGateView;
import logsim.view.component.impl.ScopeView;
import logsim.view.component.impl.SequenceGeneratorView;
import logsim.view.component.impl.SevenSegmentDisplayView;
import logsim.view.component.impl.ToggleView;
import logsim.view.component.impl.VccView;

/**
 * Az egyes alkatrészekhez létrehozza a "megjeleníthetõ" wrapper objektumokat.
 *
 */
public interface ComponentViewCreator {

    /**
     * Megjeleníthetõ ÉS kapu létrehozása
     * @param ag Becsomagolt ÉS kapu
     * @return
     */
    AndGateView createView(AndGate ag);

    /**
     * Megjeleníthetõ VAGY kapu létrehozása
     * @param og Becsomagolt VAGY kapu
     * @return
     */
    OrGateView createView(OrGate og);

    /**
     * Megjeleníthetõ vezeték létrehozása
     * @param wire Becsomagolt vezeték
     * @param start Kezdõpont
     * @param end Végpont
     * @return
     */
    WireView createView(Wire wire, Point start, Point end);

    /**
     * Megjeleníthetõ LED komponens létrehozása
     * @param led Becsomagolt LED komponens
     * @return
     */
    LedView createView(Led led);

    /**
     * Megjeleníthetõ Scope komponens létrehozása
     * @param scope Becsomagolt oszcilloszkóp
     * @return
     */
    ScopeView createView(Scope scope);

    /**
     * Megjeleníthetõ Kapcsoló komponens létrehozása
     * @param toggle Becsomagolt Kapcsoló komponens
     * @return
     */
    ToggleView createView(Toggle toggle);

    /**
     * Megjeleníthetõ Inverter komponens létrehozása
     * @param inv Becsomagolt Inverter komponens
     * @return
     */
    InverterView createView(Inverter inv);

    /**
     * Megjeleníthetõ Multiplexer komponens létrehozása
     * @param ag Becsomagolt Multiplexer komponens
     * @return
     */
    MpxView createView(Mpx mpx);

    /**
     * Megjeleníthetõ Hétszegmenses komponens létrehozása
     * @param ssd Becsomagolt Hétszegmenses komponens
     * @return
     */
    SevenSegmentDisplayView createView(SevenSegmentDisplay ssd);

    /**
     * Megjeleníthetõ GND komponens létrehozása
     * @param gnd Becsomagolt GND komponens
     * @return
     */
    GndView createView(Gnd gnd);

    /**
     * Megjeleníthetõ VCC komponens létrehozása
     * @param vcc Becsomagolt VCC komponens
     * @return
     */
    VccView createView(Vcc vcc);

    /**
     * Megjeleníthetõ Node komponens létrehozása
     * @param node Becsomagolt Node komponens
     * @return
     */
    NodeView createView(Node node);

    /**
     * Megjeleníthetõ JK flip-flop létrehozása
     * @param ff Becsomagolt JK FF komponens
     * @return
     */
    FlipFlopJKView createView(FlipFlopJK ff);

    /**
     * Megjeleníthetõ jelgenerátor létrehozása
     * @param sg
     * @return
     */
    SequenceGeneratorView createView(SequenceGenerator sg);

    /**
     * Megjeleníthetõ D flip-flop létrehozása
     * @param ff Becsomagolt D FF komponens
     * @return
     */
    FlipFlopDView createView(FlipFlopD ff);

    /**
     * Megjeleníthetõ Kompozit létrehozása
     * @param c Becsomagolt Kompozit komponens
     * @return
     */
    CompositeView createView(Composite c);
}
