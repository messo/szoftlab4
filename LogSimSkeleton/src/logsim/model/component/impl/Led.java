package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
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
     * Visszaadja a led �rt�k�t
     * @return �rt�k
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
        //bemenet�n l�v� adat lek�r�se
        Value v = inputs[0].getValue();

        //led �llapot�nak ki�r�sa
        Logger.logComment((v == Value.TRUE) ? "vil�g�t" : "nem vil�g�t");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Led";
    }
}
