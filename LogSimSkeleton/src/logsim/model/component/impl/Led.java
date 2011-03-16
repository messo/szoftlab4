package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 *
 */
public class Led extends DisplayComponent {

    /**
     * Konstruktor
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
        return inputs[0].getValue();
    }
    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, inputs[0].getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        //bemenetén lévõ adat lekérése
        Value v = inputs[0].getValue();

        //led állapotának kiírása
        Logger.logComment((v == Value.TRUE) ? "világít" : "nem világít");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Led";
    }
}
