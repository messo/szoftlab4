package logsim;

import java.awt.Point;
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

/**
 * Az alkalmazás vezérlõje
 */
public class GuiController implements Controller, ComponentViewCreator {

    private final Simulation simulation;
    private final FrameView v;
    private Circuit c;
    private Config config;
    private Map<Drawable, Point> coords;
    private List<Drawable> drawables;

    public GuiController() {
        simulation = new Simulation();
        v = new Frame(this);
    }

    @Override
    public Drawable createView(AndGate ag) {
        return new AndGateView(ag);
    }

    @Override
    public Drawable createView(Wire wire) {
        return new WireView(wire);
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
        c = new Parser().parse(new File(fileName));
        config = new Config(c);
        simulation.setCircuit(c);

        // Rajzok létrehozása...

        Collection<AbstractComponent> components = c.getComponents();
        Set<Wire> wires = new HashSet<Wire>();
        coords = new HashMap<Drawable, Point>();
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

        drawables = new ArrayList<Drawable>(wires.size() + components.size());
        for (AbstractComponent ac : components) {
            drawables.add(ac.createView(this));
        }
        for (Wire wire : wires) {
            drawables.add(wire.createView(this));
        }

        v.drawCircuit(drawables, coords);
    }

    @Override
    public void loadConfiguration(String fileName) {
        config.load(new File(fileName));
        v.drawCircuit(drawables, coords);
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
        v.drawCircuit(drawables, coords);
    }

    @Override
    public void onComponentClick(AbstractComponent ag) {
        // ablak nyitása, adatokkal feltöltve.
    }

    @Override
    public void onComponentClick(Toggle toggle) {
        toggle.setValues(new Value[]{
                    toggle.getValues()[0].invert()
                });
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
