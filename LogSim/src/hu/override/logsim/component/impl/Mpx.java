package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.Value;

/**
 * 4-1-es multiplexer, melynek a bemeneti lábak sorrendje a következő:
 * D0, D1, D2, D3, S0, S1. Ahol Dx az adatbemenetek, Sy a kiválasztóbemenetek.
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

    @Override
    protected void onEvaluation() {
        int selected = 0;
        if (evaluateInput(SEL0) == Value.TRUE) {
            selected += 1;
        }
        if (evaluateInput(SEL1) == Value.TRUE) {
            selected += 2;
        }

        // selected értéke pont egy DATAx lesz.
        currentValue[0] = inputs[selected].evaluate(indices[selected]);
    }
}
