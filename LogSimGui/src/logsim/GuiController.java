package logsim;

import java.awt.Point;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;
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
import logsim.model.component.Wire;
import logsim.model.component.impl.AndGate;
import logsim.view.Drawable;
import logsim.view.FrameView;
import logsim.view.WireView;
import logsim.view.component.AndGateView;
import logsim.view.component.InverterView;
import logsim.view.component.LedView;
import logsim.view.component.ToggleView;

/**
 * Az alkalmaz�s vez�rl�je
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
    public WireView createView(Wire wire) {
        return new WireView(wire);
    }

    @Override
    public LedView createView(Led led) {
        return new LedView(led);
    }

    @Override
    public ToggleView createView(Toggle toggle) {
        return new ToggleView(toggle);
    }

    @Override
    public InverterView createView(Inverter inv) {
        return new InverterView(inv);
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

        List<Drawable> drawables = new ArrayList<Drawable>(wires.size() + components.size());
        for (AbstractComponent ac : components) {
            Drawable d = ac.createView(this);
            positions.put(d, p.getPosition(ac));
            drawables.add(d);
        }
        for (Wire wire : wires) {
            WireView wv = wire.createView(this);
            wv.setReferencePoints(p.getReferencePoints(wire));
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
        throw new UnsupportedOperationException("Not supported yet.");
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
        // ablak nyit�sa, adatokkal felt�ltve.
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
        // ablak megjelen�t�s
    }

    @Override
    public void onComponentClick(Scope scope) {
        // ablak megjelen�t�s
    }
}