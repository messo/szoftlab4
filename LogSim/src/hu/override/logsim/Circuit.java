package hu.override.logsim;

import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.controller.Simulation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Áramkört reprezenetál, melyhez komponeseket lehet adni, és kiértékelési ciklusokat
 * lehet futtatni, utóbbi a {@link Simulation} feladata.
 *
 * @author balint
 */
public class Circuit {

    private HashMap<String, Component> componentMap;
    private boolean stable;
    private Simulation simulation;

    public Circuit() {
        componentMap = new HashMap<String, Component>();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Lekérünk egy komponenst az áramkörtõl a neve alapján
     * 
     * @param name komponens neve
     * @return komponens
     */
    public Component getComponentByName(String name) {
        return componentMap.get(name);
    }

    /**
     * Komponens hozzáadása az áramkörhöz, meghívódik a
     * {@link Component#setParent(hu.override.logsim.Circuit)} metódus is.
     *
     * @param component
     * @return
     */
    public Component addComponent(Component component) {
        component.setCircuit(this);
        componentMap.put(component.getName(), component);
        return component;
    }

    /**
     * 
     */
    public void doEvaluationCycle() {
        setStable(true);

        // számold ki magad flagek törlése, mivel új ciklus indul
        // ezért mindenkinek ki kell magát számolni újból.
        for (Component c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }

        // a megjelenítõkre hívjuk meg az evaluate();
        for (Component c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                c.evaluate();
            }
        }
    }

    public boolean isStable() {
        return stable;
    }

    public void setStable(boolean stable) {
        this.stable = stable;
    }

    public void stepGenerators() {
        synchronized (simulation.getLock()) {
            for (Component c : componentMap.values()) {
                if (c instanceof SequenceGenerator) {
                    ((SequenceGenerator) c).step();
                }
            }
        }

        simulationRefreshRequired();
    }

    /**
     * Megjelenítõ típusú komponeseket adja vissza.
     * 
     * @return
     */
    public List<Component> getDisplays() {
        List<Component> list = new ArrayList<Component>();
        for (Component c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                list.add(c);
            }
        }
        return list;
    }

    /**
     * 
     */
    public void simulationRefreshRequired() {
        synchronized (simulation.getLock()) {
            simulation.sourcesChanged();
        }
    }

    public List<Component> getSources() {
        List<Component> list = new ArrayList<Component>();
        for (Component c : componentMap.values()) {
            if (c instanceof IsSource) {
                list.add(c);
            }
        }
        return list;
    }
}
