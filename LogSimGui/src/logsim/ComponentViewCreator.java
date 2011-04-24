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
 * Az egyes alkatr�szekhez l�trehozza a "megjelen�thet�" wrapper objektumokat.
 *
 */
public interface ComponentViewCreator {

    /**
     * Megjelen�thet� �S kapu l�trehoz�sa
     * @param ag Becsomagolt �S kapu
     * @return
     */
    AndGateView createView(AndGate ag);

    /**
     * Megjelen�thet� VAGY kapu l�trehoz�sa
     * @param og Becsomagolt VAGY kapu
     * @return
     */
    OrGateView createView(OrGate og);

    /**
     * Megjelen�thet� vezet�k l�trehoz�sa
     * @param wire Becsomagolt vezet�k
     * @param start Kezd�pont
     * @param end V�gpont
     * @return
     */
    WireView createView(Wire wire, Point start, Point end);

    /**
     * Megjelen�thet� LED komponens l�trehoz�sa
     * @param led Becsomagolt LED komponens
     * @return
     */
    LedView createView(Led led);

    /**
     * Megjelen�thet� Scope komponens l�trehoz�sa
     * @param scope Becsomagolt oszcilloszk�p
     * @return
     */
    ScopeView createView(Scope scope);

    /**
     * Megjelen�thet� Kapcsol� komponens l�trehoz�sa
     * @param toggle Becsomagolt Kapcsol� komponens
     * @return
     */
    ToggleView createView(Toggle toggle);

    /**
     * Megjelen�thet� Inverter komponens l�trehoz�sa
     * @param inv Becsomagolt Inverter komponens
     * @return
     */
    InverterView createView(Inverter inv);

    /**
     * Megjelen�thet� Multiplexer komponens l�trehoz�sa
     * @param ag Becsomagolt Multiplexer komponens
     * @return
     */
    MpxView createView(Mpx mpx);

    /**
     * Megjelen�thet� H�tszegmenses komponens l�trehoz�sa
     * @param ssd Becsomagolt H�tszegmenses komponens
     * @return
     */
    SevenSegmentDisplayView createView(SevenSegmentDisplay ssd);

    /**
     * Megjelen�thet� GND komponens l�trehoz�sa
     * @param gnd Becsomagolt GND komponens
     * @return
     */
    GndView createView(Gnd gnd);

    /**
     * Megjelen�thet� VCC komponens l�trehoz�sa
     * @param vcc Becsomagolt VCC komponens
     * @return
     */
    VccView createView(Vcc vcc);

    /**
     * Megjelen�thet� Node komponens l�trehoz�sa
     * @param node Becsomagolt Node komponens
     * @return
     */
    NodeView createView(Node node);

    /**
     * Megjelen�thet� JK flip-flop l�trehoz�sa
     * @param ff Becsomagolt JK FF komponens
     * @return
     */
    FlipFlopJKView createView(FlipFlopJK ff);

    /**
     * Megjelen�thet� jelgener�tor l�trehoz�sa
     * @param sg
     * @return
     */
    SequenceGeneratorView createView(SequenceGenerator sg);

    /**
     * Megjelen�thet� D flip-flop l�trehoz�sa
     * @param ff Becsomagolt D FF komponens
     * @return
     */
    FlipFlopDView createView(FlipFlopD ff);

    /**
     * Megjelen�thet� Kompozit l�trehoz�sa
     * @param c Becsomagolt Kompozit komponens
     * @return
     */
    CompositeView createView(Composite c);
}
