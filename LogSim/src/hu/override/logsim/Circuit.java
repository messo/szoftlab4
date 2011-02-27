package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.component.impl.SequenceGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * �ramk�rt reprezent�l, melyhez komponeseket lehet adni, �s ki�rt�kel�si ciklusokat
 * lehet futtatni, ut�bbi a {@link Simulation} feladata.
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
     * Szimul�ci� be�ll�t�sa.
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
        if( component instanceof SequenceGenerator ) {
            sequenceGens.add((SequenceGenerator) component);
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
            for (AbstractComponent c : componentMap.values()) {
                if (c instanceof SequenceGenerator) {
                    ((SequenceGenerator) c).step();
                }
            }
        }

        simulationShouldBeWorking();
    }

    /**
     * Megjelen�t� t�pus� komponeseket adja vissza.
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
