package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 * 
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
        evaluateInput(0);

        // vezet�k �j �rt�k�nek bek�r�se
        Value v2 = Logger.logAskValue(this, "mit adjunk a vezet�kre");
        outputs[0].setValue(v2);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Inverter";
    }


}
