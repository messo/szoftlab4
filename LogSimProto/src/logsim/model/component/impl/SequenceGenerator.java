package logsim.model.component.impl;

import java.util.Arrays;
import logsim.Viewable;
import logsim.model.Value;
import logsim.model.component.Composite;
import logsim.model.component.SourceComponent;

/**
 * Jelgenerátort reprezentál, amely a beállított bitsorozatot adja ki. A
 * SequenceGeneratorStepper feladata, hogy a step() metódust meghívja ezen osztály
 * példányain. Azokat a FF-eket vezérli, melyek órajel bemenetére ez a komponens van kötve,
 * vagyis ha éppen felfutó él jön, akkor ezeket engedélyezi különben nem.
 * Alapértelmezetten (amíg a felhasználó nem állítja be, vagy tölt be másikat) a 0,1-es
 * szekvenciát tárolja.
 *
 * @author balint
 */
public class SequenceGenerator extends SourceComponent {

    /**
     * Tárolt bitsorozat
     */
    private Value[] sequence;
    /**
     * Bitsorozat egy indexe, ez határozza meg, hogy éppen melyik értéket adja ki.
     */
    private int index;

    /**
     * Konstruktor, ami alapállapotban a 0,1-es szekvenciát állítja be.
     */
    public SequenceGenerator(String name) {
        super(name);
        sequence = new Value[2];
        sequence[0] = Value.FALSE;
        sequence[1] = Value.TRUE;
    }

    /**
     * A jelgenerátor lép, a bitsorozat következõ elemére ugrik. A következõ léptetésig
     * ez kerül kiadásra a kimeneteken.
     */
    public void step() {
        index = (index + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(sequence[index]);
    }

    /**
     * Jelgenerátor bitsorozatának beállítása
     *
     * @return
     */
    @Override
    public void setValues(Value[] values) {
        this.sequence = Arrays.copyOf(values, values.length);
    }

    /**
     * Jelgenerátor bitsorozatának lekérdezése
     * 
     * @return
     */
    @Override
    public Value[] getValues() {
        return sequence;
    }

    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }

    @Override
    public SequenceGenerator copy(String newName) {
        SequenceGenerator sg = new SequenceGenerator(name);
        sg.setValues(sequence);
        return sg;
    }

    @Override
    public void writeValueTo(Viewable view) {
        view.writeSequenceGeneratorValue(this);
    }
}
