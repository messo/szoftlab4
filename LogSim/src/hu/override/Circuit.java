package hu.override;

import hu.override.component.AndGate;
import hu.override.component.Component;
import hu.override.component.Inverter;
import hu.override.component.Led;
import hu.override.component.OrGate;
import hu.override.component.SequenceGenerator;
import hu.override.component.Toggle;
import hu.override.exception.UnknownComponentException;
import java.util.HashMap;

/**
 *
 * @author balint
 */
public class Circuit {

    HashMap<String, Class<? extends Component>> availableComponents;
    HashMap<String, Component> componentMap;

    public Circuit() {
        availableComponents = new HashMap<String, Class<? extends Component>>(5);
        availableComponents.put("and", AndGate.class);
        availableComponents.put("or", OrGate.class);
        availableComponents.put("not", Inverter.class);
        availableComponents.put("toggle", Toggle.class);
        availableComponents.put("seqgen", SequenceGenerator.class);
        availableComponents.put("led", Led.class);

        componentMap = new HashMap<String, Component>();
    }

    public void addComponent(String componentName, String variableName, String[] arguments) throws UnknownComponentException {
        Class<? extends Component> clazz = availableComponents.get(componentName);
        if (clazz != null) {
            Component c = null;
            try {
                c = clazz.newInstance();
            } catch (Exception ex) {
                System.err.println("Nem sikerült példányosítani a komponenst!");
                ex.printStackTrace(System.err);
            }

            if (c != null) {
                c.init(arguments);
                componentMap.put(variableName, c);
            }
        } else {
            throw new UnknownComponentException();
        }
    }

    public void list() {
        for (String s : componentMap.keySet()) {
            System.out.println(s + ": " + componentMap.get(s).toString());
        }
    }

    public HashMap<String, Component> getComponentMap() {
        return componentMap;
    }
}
