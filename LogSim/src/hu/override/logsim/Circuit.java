package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.component.impl.SequenceGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Áramkört reprezentál, melyhez komponeseket lehet adni, és kiértékelési ciklusokat
 * lehet futtatni, utóbbi a {@link Simulation} feladata.
 *
 * @author balint
 */
public class Circuit {

    private HashMap<String, AbstractComponent> componentMap;
    private List<SequenceGenerator> sequenceGens;
    private boolean stable;
    private Simulation simulation;

    public Circuit() {
        componentMap = new HashMap<String, AbstractComponent>();
        sequenceGens = new ArrayList<SequenceGenerator>();
    }

    /**
     * Szimuláció beállítása.
     *
     * @param simulation
     */
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Lekérünk egy komponenst az áramkörtõl a neve alapján.
     * 
     * @param name komponens neve
     * @return komponens
     */
    public AbstractComponent getComponentByName(String name) {
        return componentMap.get(name);
    }

    /**
     * Komponens hozzáadása az áramkörhöz.
     *
     * @param component
     * @return
     */
    public AbstractComponent addComponent(AbstractComponent component) {
        component.setCircuit(this);
        componentMap.put(component.getName(), component);
        if( component instanceof SequenceGenerator ) {
            sequenceGens.add((SequenceGenerator) component);
        }
        return component;
    }

    /**
     * Egy kiértékelési ciklus lefuttatása. Az áramkörtõl ezután lekérdezhetõ, hogy
     * stabil (nem változott semelyik komponens kimenete az utolsó futtatás óta)
     * vagy instabil állapotban van-e.
     */
    public void doEvaluationCycle() {
        // kezdetben stabil
        setStable(true);

        // számold ki magad flagek törlése, mivel új ciklus indul
        // ezért mindenkinek ki kell magát számolni újból.
        for (AbstractComponent c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }

        // a megjelenítõkre hívjuk meg az evaluate();
        for (AbstractComponent c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                // miközben minden kiértékelõdik, lehet, hogy valamelyik
                // komponens instabillá teszi az áramkört, mert változott
                // az õ értéke.
                c.evaluate();
            }
        }
    }

    /**
     * Áramkör stacionárius állapotának lekérdezése.
     *
     * @return stabil-e?
     */
    public boolean isStable() {
        return stable;
    }

    /**
     * Áramkör stabilitásának beállítása.
     *
     * @param stable
     */
    public void setStable(boolean stable) {
        this.stable = stable;
    }

    /**
     * Jelgenerátorok a szimuláció szemszögébõl nézve, egyszerre történõ
     * léptetése.
     */
    public void stepGenerators() {
        synchronized (simulation.getLock()) {
            for (AbstractComponent c : componentMap.values()) {
                if (c instanceof SequenceGenerator) {
                    ((SequenceGenerator) c).step();
                }
            }
        }

        simulationShouldBeWorking();
    }

    /**
     * Megjelenítõ típusú komponeseket adja vissza.
     * 
     * @return
     */
    public List<IsDisplay> getDisplays() {
        List<IsDisplay> list = new ArrayList<IsDisplay>();
        for (AbstractComponent c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                list.add((IsDisplay) c);
            }
        }
        return list;
    }

    /**
     * Jelzi a szimuláció felé, hogy új ciklust kell indítani. Ezt egy jelforrás
     * beállítása után hívjuk meg.
     */
    public void simulationShouldBeWorking() {
        synchronized (simulation.getLock()) {
            simulation.setState(Simulation.State.WORKING);
        }
    }

    /**
     * Jelforrás típusú komponenseket adja vissza.
     */
    public List<IsSource> getSources() {
        List<IsSource> list = new ArrayList<IsSource>();
        for (AbstractComponent c : componentMap.values()) {
            if (c instanceof IsSource) {
                list.add((IsSource) c);
            }
        }
        return list;
    }

    public List<SequenceGenerator> getSequenceGenerators() {
        return sequenceGens;
    }
}
