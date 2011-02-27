package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.parser.SourceReader;
import hu.override.logsim.parser.SourceWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Feladata a jelgenerátor léptetõ kérésére a jelgenerátorok léptetése, a feldolgozó
 * által létrehozott komponensek felvétele az áramkörbe, illetve ezek utasítása arra,
 * hogy töröljék a "már kiértékelve" flaget egy adott kiértékelési ciklus elõtt, hogy ezáltal a
 * ciklusban minden kimenet értéke frissülhessen.
 * Továbbá feladata a kiértékelés elindítása az összes kijelzõre, mert a rendszer kiértékelése
 * a kijelzõk kiértékelésével kezdõdik.
 *
 * @author balint
 */
public class Circuit {

    /**
     * Komponenseket tartalmazó HashMap
     */
    private HashMap<String, AbstractComponent> componentMap;
    /**
     * Jelforrás típusú komponensek
     */
    private List<IsSource> sources;
    /**
     * Megjelenítõ típusú komponensek
     */
    private List<IsDisplay> displays;
    /**
     * Áramkör stabilitása
     */
    private boolean stable;
    /**
     * Áramkört éppen szimuláló objektum
     */
    private Simulation simulation;

    public Circuit() {
        componentMap = new HashMap<String, AbstractComponent>();
        sources = new ArrayList<IsSource>();
        displays = new ArrayList<IsDisplay>();
    }

    /**
     * Szimuláció beállítása. Ez a szimuláció létrejöttekor hívódik meg.
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
        if (component instanceof IsDisplay) {
            displays.add((IsDisplay) component);
        }
        if (component instanceof IsSource) {
            sources.add((IsSource) component);
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
            for (IsSource source : sources) {
                if (source instanceof SequenceGenerator) {
                    ((SequenceGenerator) source).step();
                }
            }
        }

        simulationShouldBeWorking();
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
        return sources;
    }

    /**
     * Megjelenítõ típusú komponeseket adja vissza.
     *
     * @return
     */
    public List<IsDisplay> getDisplays() {
        return displays;
    }

    /**
     * Fájlból betölti a jelforrások állapotát.
     *
     * @param fileName
     * @return visszajelzés
     */
    public int loadSources(String fileName) {
        SourceReader sr = new SourceReader(fileName);
        sr.loadValuesToSources(sources);

        return 0;
    }

    /**
     * Elmenti fájlba a jelforrások állapotát.
     *
     * @return visszajelzés
     */
    public int saveSources(String fileName) {
        SourceWriter sw = new SourceWriter(fileName);
        for (IsSource source : sources) {
            sw.add(source);
        }
        sw.close();

        return 0;
    }
}
