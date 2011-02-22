package hu.override;

import hu.override.component.Component;
import hu.override.component.HasDirtyFlag;
import hu.override.component.IsDisplay;
import hu.override.component.SequenceGenerator;
import hu.override.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author balint
 */
public class Circuit implements HasDirtyFlag {

    private HashMap<String, Component> componentMap;
    private View view;
    private boolean dirty;

    public Circuit() {
        componentMap = new HashMap<String, Component>();
    }

    public Component getComponentByName(String name) {
        return componentMap.get(name);
    }

    public Component addComponent(Component component) {
        component.setParent(this);
        componentMap.put(component.getName(), component);
        return component;
    }

    public void simulate() {
        // 1. számold ki magad flagek törlése
        for (Component c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }
        // 2. for ciklussal a megjelenítõkre hívjuk meg az evaluate();
        for (Component c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                c.evaluate();
            }
        }
    }

    public boolean isChanged() {
        return dirty;
    }

    public void stepGenerators() {
        for (Component c : componentMap.values()) {
            if (c instanceof SequenceGenerator) {
                ((SequenceGenerator) c).step();
            }
        }
    }

    public List<Component> getDisplays() {
        List<Component> list = new ArrayList<Component>();
        for (Component c : componentMap.values()) {
            if (c instanceof IsDisplay) {
                list.add(c);
            }
        }
        return list;
    }

    public void setDirtyFlag() {
        dirty = true;
    }

    public void clearDirtyFlag() {
        dirty = false;
    }
}
