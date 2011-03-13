package logsim.model.component.impl;

import logsim.model.component.FlipFlop;

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
    private static final int D = 1;

    public FlipFlopD(String name) {
        super(name);
    }

    @Override
    protected void onEvaluation() {
        if (isActive()) {
            // bemenet�n l�v� �rt�ket be�rjuk.
            outputs[0].setValue(evaluateInput(D));
        } else {
            // marad a r�gi.
            outputs[0].setValue(q);
        }
    }
}
