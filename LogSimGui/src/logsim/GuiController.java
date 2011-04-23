package logsim;

import java.awt.Point;
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
import logsim.view.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Pin;
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.view.Drawable;
import logsim.view.FrameView;
import logsim.view.component.ComponentView;
import logsim.view.component.WireView;
import logsim.view.component.impl.AndGateView;
import logsim.view.component.impl.GndView;
import logsim.view.component.impl.InverterView;
import logsim.view.component.impl.LedView;
import logsim.view.component.impl.MpxView;
import logsim.view.component.impl.NodeView;
import logsim.view.component.impl.OrGateView;
import logsim.view.component.impl.SevenSegmentDisplayView;
import logsim.view.component.impl.ToggleView;
import logsim.view.component.impl.VccView;

/**
 * Az alkalmazás vezérlõje
 */
public class GuiController implements Controller, ComponentViewCreator {

    private final Simulation simulation;
    private final FrameView v;
    private Circuit c;
    private Config config;

    public GuiController() {
        simulation = new Simulation();
        v = new Frame(this);
    }

    @Override
    public AndGateView createView(AndGate ag) {
        return new AndGateView(ag);
    }

    @Override
    public OrGateView createView(OrGate og) {
        return new OrGateView(og);
    }

    @Override
    public WireView createView(Wire wire, Point start, Point end) {
        return new WireView(wire, start, end);
    }

    @Override
    public LedView createView(Led led) {
        return new LedView(led);
    }

    @Override
    public SevenSegmentDisplayView createView(SevenSegmentDisplay ssd) {
        return new SevenSegmentDisplayView(ssd);
    }

    @Override
    public ToggleView createView(Toggle toggle) {
        return new ToggleView(toggle);
    }

    @Override
    public InverterView createView(Inverter inv) {
        return new InverterView(inv);
    }

    @Override
    public MpxView createView(Mpx mpx) {
        return new MpxView(mpx);
    }

    @Override
    public NodeView createView(Node node) {
        return new NodeView(node);
    }

    @Override
    public GndView createView(Gnd gnd) {
        return new GndView();
    }

    @Override
    public VccView createView(Vcc vcc) {
        return new VccView();
    }

    public static void main(String[] args) {
        GuiController c = new GuiController();
        c.run();
    }

    private void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                v.makeItVisible();
            }
        });
    }

    @Override
    public void loadCircuit(String fileName) {
        Parser p = new Parser();
        c = p.parse(new File(fileName));
        config = new Config(c);
        simulation.setCircuit(c);

        // Rajzok létrehozása...

        Collection<AbstractComponent> components = c.getComponents();
        Set<Wire> wires = new HashSet<Wire>();
        Map<Drawable, Point> positions = new HashMap<Drawable, Point>();
        for (AbstractComponent ac : components) {
            Wire w;
            for (int i = 1; i <= ac.getInputsCount(); i++) {
                w = ac.getInputWire(i);
                if (w != null) {
                    wires.add(w);
                }
            }
            for (int i = 1; i <= ac.getOutputsCount(); i++) {
                w = ac.getOutputWire(i);
                if (w != null) {
                    wires.add(w);
                }
            }
        }

        Map<AbstractComponent, ComponentView> views = new HashMap<AbstractComponent, ComponentView>();
        List<Drawable> drawables = new ArrayList<Drawable>(wires.size() + components.size());
        for (AbstractComponent ac : components) {
            ComponentView cv = ac.createView(this);
            positions.put(cv, p.getPosition(ac));
            drawables.add(cv);
            views.put(ac, cv);
        }
        for (Wire wire : wires) {
            Pin output = c.getOutputPinForWire(wire);
            Pin input = c.getInputPinForWire(wire);

            ComponentView outputView = views.get(output.getComponent());
            ComponentView inputView = views.get(input.getComponent());

            Point relStart = outputView.getRelativeOutputPinPosition(output.getPin());
            Point relEnd = inputView.getRelativeInputPinPosition(input.getPin());

            if(positions.get(outputView) == null) {
                // nincs pozíciója, akkor rakjuk le mi
                Point pos = positions.get(inputView).getLocation();
                pos.translate(relEnd.x, relEnd.y);
                pos.translate(-30, -outputView.getDimension().height / 2);
                positions.put(outputView, pos);
            }

            Point start = positions.get(outputView).getLocation();
            Point end = positions.get(inputView).getLocation();

            start.translate(relStart.x, relStart.y);
            end.translate(relEnd.x, relEnd.y);

            WireView wv = wire.createView(this, start, end);
            
            wv.setReferencePoints(p.getReferencePoints(input));

            drawables.add(wv);
        }

        v.setDrawables(drawables, positions);
        v.drawCircuit();
    }

    @Override
    public void loadConfiguration(String fileName) {
        config.load(new File(fileName));
        v.drawCircuit();
    }

    @Override
    public void saveConfiguration(String fileName) {
        config.save(new File(fileName));
    }

    @Override
    public void onStep() {
        if (simulation.start()) {
            v.onSuccessfulSimulation();
        } else {
            v.onFailedSimulation();
        }
        v.drawCircuit();
    }

    @Override
    public void onComponentClick(AbstractComponent ag) {
        System.out.println("Clicked on: " + ag);
    }

    @Override
    public void onComponentClick(Toggle toggle) {
        toggle.setValues(new Value[]{
                    toggle.getValues()[0].invert()
                });
        v.drawCircuit();
    }

    @Override
    public void onComponentClick(SequenceGenerator sg) {
        // ablak megjelenítés
    }

    @Override
    public void onComponentClick(Scope scope) {
        // ablak megjelenítés
    }
}
