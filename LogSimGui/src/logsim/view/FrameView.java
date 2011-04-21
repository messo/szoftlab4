package logsim.view;

import logsim.Controller;

/**
 *
 */
public interface FrameView {

    void onSuccessfulSimulation();

    void onFailedSimulation();

    void makeItVisible();

    Controller getController();

    void setCircuitView(CircuitView circuitView);

    void drawCircuit();
}
