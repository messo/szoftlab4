package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.component.Component;
import hu.override.logsim.controller.Simulation;

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
