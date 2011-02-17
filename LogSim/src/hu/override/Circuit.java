package hu.override;

import hu.override.component.Component;
import java.util.HashMap;

/**
 *
 * @author balint
 */
public class Circuit {

    private HashMap<String, Component> componentMap;

    public Circuit() {
        componentMap = new HashMap<String, Component>();
    }

    public HashMap<String, Component> getComponentMap() {
        return componentMap;
    }

    public void addComponent(Component component) {
        componentMap.put(component.getName(), component);
    }

    public void list() {
        for (String s : componentMap.keySet()) {
            System.out.println(s + ": " + componentMap.get(s).toString());
        }
    }
}
