package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 */
public class Inverter extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenet �s 1 kimenet
     * @param name Inverter neve
     */
    public Inverter(String name) {
        super(name, 1, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // bemenet�n l�v� adat lek�rdez�se
        getInput(0);

        // vezet�k �j �rt�k�nek bek�r�se
        Value v = Logger.logAskValue(this, "mit adjunk a vezet�kre");

        // kimenet be�ll�t�sa
        outputs[0].setValue(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Inverter";
    }
}
