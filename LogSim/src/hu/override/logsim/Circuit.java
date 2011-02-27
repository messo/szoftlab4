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
 * Feladata a jelgener�tor l�ptet� k�r�s�re a jelgener�torok l�ptet�se, a feldolgoz�
 * �ltal l�trehozott komponensek felv�tele az �ramk�rbe, illetve ezek utas�t�sa arra,
 * hogy t�r�lj�k a "m�r ki�rt�kelve" flaget egy adott ki�rt�kel�si ciklus el�tt, hogy ez�ltal a
 * ciklusban minden kimenet �rt�ke friss�lhessen.
 * Tov�bb� feladata a ki�rt�kel�s elind�t�sa az �sszes kijelz�re, mert a rendszer ki�rt�kel�se
 * a kijelz�k ki�rt�kel�s�vel kezd�dik.
 *
 * @author balint
 */
public class Circuit {

    /**
     * Komponenseket tartalmaz� HashMap
     */
    private HashMap<String, AbstractComponent> componentMap;
    /**
     * Jelforr�s t�pus� komponensek
     */
    private List<IsSource> sources;
    /**
     * Megjelen�t� t�pus� komponensek
     */
    private List<IsDisplay> displays;
    /**
     * �ramk�r stabilit�sa
     */
    private boolean stable;
    /**
     * �ramk�rt �ppen szimul�l� objektum
     */
    private Simulation simulation;

    public Circuit() {
        componentMap = new HashMap<String, AbstractComponent>();
        sources = new ArrayList<IsSource>();
        displays = new ArrayList<IsDisplay>();
    }

    /**
     * Szimul�ci� be�ll�t�sa. Ez a szimul�ci� l�trej�ttekor h�v�dik meg.
     *
     * @param simulation
     */
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Lek�r�nk egy komponenst az �ramk�rt�l a neve alapj�n.
     * 
     * @param name komponens neve
     * @return komponens
     */
    public AbstractComponent getComponentByName(String name) {
        return componentMap.get(name);
    }

    /**
     * Komponens hozz�ad�sa az �ramk�rh�z.
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
     * Egy ki�rt�kel�si ciklus lefuttat�sa. Az �ramk�rt�l ezut�n lek�rdezhet�, hogy
     * stabil (nem v�ltozott semelyik komponens kimenete az utols� futtat�s �ta)
     * vagy instabil �llapotban van-e.
     */
    public void doEvaluationCycle() {
        // kezdetben stabil
        setStable(true);

        // sz�mold ki magad flagek t�rl�se, mivel �j ciklus indul
        // ez�rt mindenkinek ki kell mag�t sz�molni �jb�l.
        for (AbstractComponent c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }

        // a megjelen�t�kre h�vjuk meg az evaluate();
        for (AbstractComponent c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                // mik�zben minden ki�rt�kel�dik, lehet, hogy valamelyik
                // komponens instabill� teszi az �ramk�rt, mert v�ltozott
                // az � �rt�ke.
                c.evaluate();
            }
        }
    }

    /**
     * �ramk�r stacion�rius �llapot�nak lek�rdez�se.
     *
     * @return stabil-e?
     */
    public boolean isStable() {
        return stable;
    }

    /**
     * �ramk�r stabilit�s�nak be�ll�t�sa.
     *
     * @param stable
     */
    public void setStable(boolean stable) {
        this.stable = stable;
    }

    /**
     * Jelgener�torok a szimul�ci� szemsz�g�b�l n�zve, egyszerre t�rt�n�
     * l�ptet�se.
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
     * Jelzi a szimul�ci� fel�, hogy �j ciklust kell ind�tani. Ezt egy jelforr�s
     * be�ll�t�sa ut�n h�vjuk meg.
     */
    public void simulationShouldBeWorking() {
        synchronized (simulation.getLock()) {
            simulation.setState(Simulation.State.WORKING);
        }
    }

    /**
     * Jelforr�s t�pus� komponenseket adja vissza.
     */
    public List<IsSource> getSources() {
        return sources;
    }

    /**
     * Megjelen�t� t�pus� komponeseket adja vissza.
     *
     * @return
     */
    public List<IsDisplay> getDisplays() {
        return displays;
    }

    /**
     * F�jlb�l bet�lti a jelforr�sok �llapot�t.
     *
     * @param fileName
     * @return visszajelz�s
     */
    public int loadSources(String fileName) {
        SourceReader sr = new SourceReader(fileName);
        sr.loadValuesToSources(sources);

        return 0;
    }

    /**
     * Elmenti f�jlba a jelforr�sok �llapot�t.
     *
     * @return visszajelz�s
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
