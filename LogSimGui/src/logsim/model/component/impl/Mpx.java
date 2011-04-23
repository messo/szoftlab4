package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.view.component.ComponentView;

/**
 * 4-1-es multiplexer, melynek a bemeneti lábak sorrendje a következõ:
 * D0, D1, D2, D3, S0, S1. Ahol Dx az adatbemenetek, Sy a kiválasztóbemenetek.
 * Kimenetén a kiválasztóbemenetektõl függõen valamelyik adatbemenet kerül kiadásra.
 *
 * @author gabooo
 */
public class Mpx extends AbstractComponent {

    private static final int DATA0 = 1; // LSB
    private static final int DATA1 = 2;
    private static final int DATA2 = 3;
    private static final int DATA3 = 4;
    private static final int SEL0 = 5; // LSB
    private static final int SEL1 = 6;

    public Mpx(String name) {
        super(name, 6, 1);
    }

    @Override
    protected void onEvaluation() {
        int selected = 1;
        if (getInput(SEL0) == Value.TRUE) {
            selected += 1;
        }
        if (getInput(SEL1) == Value.TRUE) {
            selected += 2;
        }

        // selected értéke pont egy DATAx lesz.
        outputs[0].setValue(getInput(selected));
    }

    @Override
    public Mpx copy(String newName) {
        return new Mpx(newName);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
