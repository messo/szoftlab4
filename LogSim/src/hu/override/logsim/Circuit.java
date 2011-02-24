package hu.override.logsim;

import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.controller.Simulation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * �ramk�rt reprezenet�l, melyhez komponeseket lehet adni, �s ki�rt�kel�si ciklusokat
 * lehet futtatni, ut�bbi a {@link Simulation} feladata.
 *
 * @author balint
 */
public class Circuit {

    private HashMap<String, Component> componentMap;
    private boolean unstable;

    public Circuit() {
        componentMap = new HashMap<String, Component>();
    }

    /**
     * Lek�r�nk egy komponenst az �ramk�rt�l a neve alapj�n
     * 
     * @param name komponens neve
     * @return komponens
     */
    public Component getComponentByName(String name) {
        return componentMap.get(name);
    }

    /**
     * Komponens hozz�ad�sa az �ramk�rh�z, megh�v�dik a
     * {@link Component#setParent(hu.override.logsim.Circuit)} met�dus is.
     *
     * @param component
     * @return
     */
    public Component addComponent(Component component) {
        component.setParent(this);
        componentMap.put(component.getName(), component);
        return component;
    }

    /**
     * 
     */
    public void doEvaluationCycle() {
        unstable = false;

        // sz�mold ki magad flagek t�rl�se, mivel �j ciklus indul
        // ez�rt mindenkinek ki kell mag�t sz�molni �jb�l.
        for (Component c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }

        // a megjelen�t�kre h�vjuk meg az evaluate();
        for (Component c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                c.evaluate();
            }
        }
    }

    public boolean isUnstable() {
        return unstable;
    }

    public void setUnstable(boolean unstable) {
        this.unstable = unstable;
    }

    public void stepGenerators() {
        for (Component c : componentMap.values()) {
            if (c instanceof SequenceGenerator) {
                ((SequenceGenerator) c).step();
            }
        }
    }

    /**
     * Megjelen�t� t�pus� komponeseket adja vissza.
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
}
