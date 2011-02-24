package hu.override.logsim;

import hu.override.logsim.component.Component;
import hu.override.logsim.component.IsDisplay;
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
    private boolean unstable;

    public Circuit() {
        componentMap = new HashMap<String, Component>();
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
        component.setParent(this);
        componentMap.put(component.getName(), component);
        return component;
    }

    /**
     * 
     */
    public void doEvaluationCycle() {
        unstable = false;

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
}
