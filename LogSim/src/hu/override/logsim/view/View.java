package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.controller.Simulation;

/**
 *
 * @author balint
 */
public interface View {

    public void update(Circuit circuit);

    public void setController(Simulation controller);
}
