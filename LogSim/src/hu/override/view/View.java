package hu.override.view;

import hu.override.Circuit;
import hu.override.controller.Simulation;

/**
 *
 * @author balint
 */
public interface View {

    public void update(Circuit circuit);

    public void setController(Simulation controller);
}
