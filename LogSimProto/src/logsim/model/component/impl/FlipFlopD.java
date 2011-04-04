package logsim.model.component.impl;

import logsim.model.component.FlipFlop;

/**
 * D flipflop, mely felfutó órajelnél beírja a belsõ memóriába az adatbemeneten (D)
 * lévõ értéket.
 *
 * @author gabooo
 */
public class FlipFlopD extends FlipFlop {

    /**
     * D bemenet lábának a száma.
     */
    private static final int D = 2;

    public FlipFlopD(String name) {
        super(name, 2);
    }

    /**
     * Flipflop logika véglegesítésnél
     */
    @Override
    protected void onCommit() {
        if (isActive()) {
            // bemenetén lévõ értéket beírjuk.
            outputs[0].setValue(getInput(D));
        } else {
            // marad a régi.
            outputs[0].setValue(q);
        }
    }

    @Override
    public FlipFlopD copy(String newName) {
        return new FlipFlopD(newName);
    }
}
