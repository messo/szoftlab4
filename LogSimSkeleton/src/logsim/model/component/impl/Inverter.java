package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    public Inverter(String name) {
        super(name);
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        // bemenet�n l�v� �rt�ket invert�lja.
        Value v = evaluateInput(0);

        Value v2 = Logger.logAskValue(this, "mit adjunk a vezet�kre");
        outputs[0].setValue(v2);
    }

    @Override
    public String getClassName() {
        return "Inverter";
    }
}
