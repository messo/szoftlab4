package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 * 
 * @author balint
 */
public class Inverter extends AbstractComponent {

    @Override
    protected void onEvaluation() {
        // bemenet�n l�v� �rt�ket invert�lja.
        Value v = evaluateInput(0).invert();
        outputs[0].setValue(v);
        System.out.println("      CALL outputs[0].setValue( [" + v + "] )");
        System.out.println("      RETURN");
    }
}
