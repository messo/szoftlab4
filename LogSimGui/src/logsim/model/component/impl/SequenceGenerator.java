package logsim.model.component.impl;

import java.util.Arrays;
import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.Composite;
import logsim.model.component.SourceComponent;
import logsim.view.component.ComponentView;

/**
 * Jelgener�tort reprezent�l, amely a be�ll�tott bitsorozatot adja ki.
 * Alap�rtelmezetten (am�g a felhaszn�l� nem �ll�tja be, vagy t�lt be m�sikat) a 0,1-es
 * szekvenci�t t�rolja.
 *
 * @author balint
 */
public class SequenceGenerator extends SourceComponent {

    /**
     * T�rolt bitsorozat
     */
    private Value[] sequence;
    /**
     * Bitsorozat egy indexe, ez hat�rozza meg, hogy �ppen melyik �rt�ket adja ki.
     */
    private int index;

    /**
     * Konstruktor, ami alap�llapotban a 0,1-es szekvenci�t �ll�tja be.
     */
    public SequenceGenerator(String name) {
        super(name);
        sequence = new Value[2];
        sequence[0] = Value.FALSE;
        sequence[1] = Value.TRUE;
    }

    /**
     * A jelgener�tor l�p, a bitsorozat k�vetkez� elem�re ugrik. A k�vetkez� l�ptet�sig
     * ez ker�l kiad�sra a kimeneteken.
     */
    public void step() {
        index = (index + 1) % sequence.length;
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(sequence[index]);
    }

    /**
     * Jelgener�tor bitsorozat�nak be�ll�t�sa
     *
     * @return
     */
    @Override
    public void setValues(Value[] values) {
        this.index = 0;
        this.sequence = Arrays.copyOf(values, values.length);
    }

    /**
     * Jelgener�tor bitsorozat�nak lek�rdez�se
     * 
     * @return
     */
    @Override
    public Value[] getValues() {
        return sequence;
    }

    /**
     * Hozz�ad�s kompozithoz.
     * @param composite
     */
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

    /**
     * Jelgener�tor alaphelyzetbe �ll�t�sa (01-es szekvencia)
     */
    @Override
    public void reset() {
        index = 0;
        sequence = new Value[2];
        sequence[0] = Value.FALSE;
        sequence[1] = Value.TRUE;
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
