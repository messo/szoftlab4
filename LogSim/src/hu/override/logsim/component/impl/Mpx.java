package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.Value;
import hu.override.logsim.component.Wire;

/**
 * 4-1-es multiplexer, melynek a bemeneti l�bak sorrendje a k�vetkez�:
 * D0, D1, D2, D3, S0, S1. Ahol Dx az adatbemenetek, Sy a kiv�laszt�bemenetek.
 * Kimenet�n a kiv�laszt�bemenetekt�l f�gg�en valamelyik adatbemenet ker�l kiad�sra.
 *
 * @author gabooo
 */
public class Mpx extends AbstractComponent {

    private static final int DATA0 = 0; // LSB
    private static final int DATA1 = 1;
    private static final int DATA2 = 2;
    private static final int DATA3 = 3;
    private static final int SEL0 = 4; // LSB
    private static final int SEL1 = 5;

    public Mpx() {
        super();
        inputs = new Wire[6];
    }

    @Override
    protected void onEvaluation() {
        int selected = 0;
        if (evaluateInput(SEL0) == Value.TRUE) {
            selected += 1;
        }
        if (evaluateInput(SEL1) == Value.TRUE) {
            selected += 2;
        }

        // selected �rt�ke pont egy DATAx lesz.
        outputs[0].setValue(evaluateInput(selected));
    }
}
