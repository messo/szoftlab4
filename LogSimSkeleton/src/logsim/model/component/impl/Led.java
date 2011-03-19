package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.DisplayComponent;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
 */
public class Led extends DisplayComponent {

    /**
     * Konstruktor. 1 bemenet� megjelen�t�
     * @param name Led neve
     */
    public Led(String name) {
        super(name, 1);
    }

    /**
     * Visszaadja a led �rt�k�t
     * @return �rt�k
     */
    public Value getValue() {
        return getInput(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // bemenet�n l�v� adat lek�r�se
        getInput(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Led";
    }
}
