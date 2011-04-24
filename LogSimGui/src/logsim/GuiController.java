package logsim;

import java.awt.Point;
import logsim.model.component.Composite;
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
 * Az alkalmaz�s vez�rl�je
 */
public class GuiController implements Controller, ComponentViewCreator {

    private final Simulation simulation;
    private final FrameView v;
    private Circuit c;
    private Config config;

    /**
     * Konstruktor
     */
    public GuiController() {
        simulation = new Simulation();
        v = new Frame(this);
    }

    /**
     * Megjelen�thet� �S kapu l�trehoz�sa
     * @param ag Becsomagolt �S kapu
     * @return
     */
    @Override
    public AndGateView createView(AndGate ag) {
        return new AndGateView(ag);
    }

    /**
     * Megjelen�thet� VAGY kapu l�trehoz�sa
     * @param og Becsomagolt VAGY kapu
     * @return
     */
    @Override
    public OrGateView createView(OrGate og) {
        return new OrGateView(og);
    }

    /**
     * Megjelen�thet� vezet�k l�trehoz�sa
     * @param wire Becsomagolt vezet�k
     * @param start Kezd�pont
     * @param end V�gpont
     * @return
     */
    @Override
    public WireView createView(Wire wire, Point start, Point end) {
        return new WireView(wire, start, end);
    }

    /**
     * Megjelen�thet� LED komponens l�trehoz�sa
     * @param led Becsomagolt LED komponens
     * @return
     */
    @Override
    public LedView createView(Led led) {
        return new LedView(led);
    }

    /**
     * Megjelen�thet� Scope komponens l�trehoz�sa
     * @param scope Becsomagolt oszcilloszk�p
     * @return
     */
    @Override
    public ScopeView createView(Scope scope) {
        return new ScopeView(scope);
    }

    /**
     * Megjelen�thet� H�tszegmenses komponens l�trehoz�sa
     * @param ssd Becsomagolt H�tszegmenses komponens
     * @return
     */
    @Override
    public SevenSegmentDisplayView createView(SevenSegmentDisplay ssd) {
        return new SevenSegmentDisplayView(ssd);
    }

    /**
     * Megjelen�thet� Kapcsol� komponens l�trehoz�sa
     * @param toggle Becsomagolt Kapcsol� komponens
     * @return
     */
    @Override
    public ToggleView createView(Toggle toggle) {
        return new ToggleView(toggle);
    }

    /**
     * Megjelen�thet� Inverter komponens l�trehoz�sa
     * @param inv Becsomagolt Inverter komponens
     * @return
     */
    @Override
    public InverterView createView(Inverter inv) {
        return new InverterView(inv);
    }

    /**
     * Megjelen�thet� Multiplexer komponens l�trehoz�sa
     * @param ag Becsomagolt Multiplexer komponens
     * @return
     */
    @Override
    public MpxView createView(Mpx mpx) {
        return new MpxView(mpx);
    }

    /**
     * Megjelen�thet� Node komponens l�trehoz�sa
     * @param node Becsomagolt Node komponens
     * @return
     */
    @Override
    public NodeView createView(Node node) {
        return new NodeView(node);
    }

    /**
     * Megjelen�thet� GND komponens l�trehoz�sa
     * @param gnd Becsomagolt GND komponens
     * @return
     */
    @Override
    public GndView createView(Gnd gnd) {
        return new GndView();
    }

    /**
     * Megjelen�thet� VCC komponens l�trehoz�sa
     * @param vcc Becsomagolt VCC komponens
     * @return
     */
    @Override
    public VccView createView(Vcc vcc) {
        return new VccView();
    }

    /**
     * Megjelen�thet� JK flip-flop l�trehoz�sa
     * @param ff Becsomagolt JK FF komponens
     * @return
     */
    @Override
    public FlipFlopJKView createView(FlipFlopJK ff) {
        return new FlipFlopJKView(ff);
    }

    /**
     * Megjelen�thet� jelgener�tor l�trehoz�sa
     * @param sg
     * @return
     */
    @Override
    public SequenceGeneratorView createView(SequenceGenerator sg) {
        return new SequenceGeneratorView(sg);
    }

    /**
     * Megjelen�thet� D flip-flop l�trehoz�sa
     * @param ff Becsomagolt D FF komponens
     * @return
     */
    @Override
    public FlipFlopDView createView(FlipFlopD ff) {
        return new FlipFlopDView(ff);
    }

    /**
     * Megjelen�thet� Kompozit l�trehoz�sa
     * @param c Becsomagolt Kompozit komponens
     * @return
     */
    @Override
    public CompositeView createView(Composite c) {
        return new CompositeView(c);
    }

    /**
     * Program bel�p�si pontja
     * @param args
     */
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

    /**
     * �ramk�r bet�lt�se
     * @param fileName �ramk�rt le�r� f�jl neve
     */
    @Override
    public void loadCircuit(String fileName) {
        Parser p = new Parser();
        c = p.parse(new File(fileName));
        config = new Config(c);
        simulation.setCircuit(c);

        // Rajzok l�trehoz�sa...

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

            if (positions.get(outputView) == null) {
                // nincs poz�ci�ja, akkor rakjuk le mi
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

    /**
     * �romk�r konfigur�ci�s f�jl bet�lt�se
     * @param fileName Konfigur�ci�t t�rol� f�jl neve
     */
    @Override
    public void loadConfiguration(String fileName) {
        config.load(new File(fileName));
        v.drawCircuit();
    }

    /**
     * Konfigur�ci�s f�jl ment�se
     * @param fileName F�jl neve
     */
    @Override
    public void saveConfiguration(String fileName) {
        config.save(new File(fileName));
    }

    /**
     * �ramk�r l�ptet�se
     */
    @Override
    public void onStep() {
        if (simulation.start()) {
            v.onSuccessfulSimulation();
        } else {
            v.onFailedSimulation();
        }
        v.drawCircuit();
    }

    /**
     * �ltal�nos komponens inform�ci� megjelen�t�s (n�v, bemenet, kimenet)
     * @param ag
     */
    @Override
    public void onComponentClick(AbstractComponent ag) {
//        System.out.println("Clicked on: " + ag);
        v.showDetails(ag);
    }

    /**
     * Kapcsol� v�ltoztat�sa
     * @param toggle
     */
    @Override
    public void onComponentClick(Toggle toggle) {
        toggle.setValues(new Value[]{
                    toggle.getValues()[0].invert()
                });
        v.drawCircuit();
    }

    /**
     * Jelgener�tor megjelen�t�se �s konfigur�l�sa
     * @param sg
     */
    @Override
    public void onComponentClick(SequenceGenerator sg) {
        // ablak megjelen�t�s

        v.showDetails(sg);
    }

    /**
     * Scope megjelen�t�s (eddig elt�rolt �rt�kek)
     * @param scope
     */
    @Override
    public void onComponentClick(Scope scope) {
        // ablak megjelen�t�s
//        for(Value value : scope.getValues()) {
//            System.out.print(value == Value.TRUE ? "1" : "0");
//        }
//        System.out.println("");
        v.showDetails(scope);
    }

    /**
     * Szimul�ci� sebess�g�nek megv�ltoztat�sa
     * @param p
     */
    @Override
    public void onPeriodChanged(int p) {
        v.setPeriod(p);
    }

    @Override
    public void onSequenceChanged(SequenceGenerator sg, String seq) {
        Value[] values = new Value[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '0') {
                values[i] = Value.FALSE;
            } else {
                values[i] = Value.TRUE;
            }
        }
        sg.setValues(values);

        v.drawCircuit();
    }
}
