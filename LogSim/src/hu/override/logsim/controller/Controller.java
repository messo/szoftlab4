package hu.override.logsim.controller;

/**
 *
 * @author balint
 */
public interface Controller {

    public void onCircuitUpdate();

    public void onStart();

    public void onStop();

    public void onExit();
}
