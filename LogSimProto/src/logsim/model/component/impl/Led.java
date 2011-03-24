package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.DisplayComponent;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 */
public class Led extends DisplayComponent {

    /**
     * Konstruktor. 1 bemenetû megjelenítõ
     * @param name Led neve
     */
    public Led(String name) {
        super(name, 1);
    }

    /**
     * Visszaadja a led értékét
     * @return Érték
     */
    public Value getValue() {
        return getInput(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // bemenetén lévõ adat lekérése
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
