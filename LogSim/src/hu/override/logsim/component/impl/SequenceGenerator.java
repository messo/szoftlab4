package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsSource;

/**
 * Jelgener�tort reprezent�l, amely a be�ll�tott bitsorozatot adja ki. A
 * SequenceGeneratorStepper feladata, hogy a step() met�dust megh�vja ezen oszt�ly
 * p�ld�nyain.
 *
 * @author balint
 */
public class SequenceGenerator extends AbstractComponent implements IsSource {

    private Value[] sequence;
    private int idx;

    /**
     * A jelgener�tor l�p, a bitsorozat k�vetkez� elem�re ugrik. A k�vetkez� l�ptet�sig
     * ez ker�l kiad�sra a kimeneteken.
     */
    public void step() {
        idx = (idx + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = sequence[idx];
    }

    @Override
    public void setValues(Value[] values) {
        this.sequence = values;
    }

    @Override
    public Value[] getValues() {
        return sequence;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount > 0;
    }
}
