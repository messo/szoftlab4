package hu.override.view;

import hu.override.Circuit;
import hu.override.component.Component;
import hu.override.controller.Simulation;

/**
 *
 * @author balint
 */
public class ConsoleView implements View {

    private Simulation controller;

    public void update(Circuit circuit) {
        for (Component c : circuit.getDisplays()) {
            System.out.println(c);
        }
        System.out.println("====");
    }

    public void setController(Simulation controller) {
        this.controller = controller;
    }
}
