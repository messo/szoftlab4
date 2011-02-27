package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsSource;

/**
 * Jelgenerátort reprezentál, amely a beállított bitsorozatot adja ki. A
 * SequenceGeneratorStepper feladata, hogy a step() metódust meghívja ezen osztály
 * példányain.
 *
 * @author balint
 */
public class SequenceGenerator extends AbstractComponent implements IsSource {

    private Value[] sequence;
    private int idx;

    /**
     * A jelgenerátor lép, a bitsorozat következõ elemére ugrik. A következõ léptetésig
     * ez kerül kiadásra a kimeneteken.
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
