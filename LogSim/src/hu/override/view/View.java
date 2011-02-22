package hu.override.view;

import hu.override.Circuit;
import hu.override.controller.CircuitController;

/**
 *
 * @author balint
 */
public interface View {

    public void update(Circuit circuit);

    public void setController(CircuitController controller);
}
