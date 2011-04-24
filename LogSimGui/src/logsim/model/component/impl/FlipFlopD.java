package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.component.FlipFlop;
import logsim.view.component.ComponentView;

/**
 * D flipflop, mely felfut� �rajeln�l be�rja a bels� mem�ri�ba az adatbemeneten (D)
 * l�v� �rt�ket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    /**
     * D bemenet l�b�nak a sz�ma.
     */
    private static final int D = 2;

    public FlipFlopD(String name) {
        super(name, 2);
    }

    /**
     * Flipflop logika v�gleges�t�sn�l
     */
    @Override
    protected void onCommit() {
        if (isActive()) {
            // bemenet�n l�v� �rt�ket be�rjuk.
            outputs[0].setValue(getInput(D));
        } else {
            // marad a r�gi.
            outputs[0].setValue(q);
        }
    }

    @Override
    public FlipFlopD copy(String newName) {
        return new FlipFlopD(newName);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
