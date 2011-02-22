package hu.override.view;

import hu.override.Circuit;
import hu.override.component.Component;
import hu.override.controller.CircuitController;

/**
 *
 * @author balint
 */
public class ConsoleView implements View {

    private CircuitController controller;

    public void update(Circuit circuit) {
        for (Component c : circuit.getDisplays()) {
            System.out.println(c);
        }
    }

    public void setController(CircuitController controller) {
        this.controller = controller;
    }
}
