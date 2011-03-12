package hu.override.logsim.component.impl;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.SourceComponent;

/**
 * Jelgener�tort reprezent�l, amely a be�ll�tott bitsorozatot adja ki. A
 * SequenceGeneratorStepper feladata, hogy a step() met�dust megh�vja ezen oszt�ly
 * p�ld�nyain. Azokat a FF-eket vez�rli, melyek �rajel bemenet�re ez a komponens van k�tve,
 * vagyis ha �ppen felfut� �l j�n, akkor ezeket enged�lyezi k�l�nben nem.
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
    public SequenceGenerator() {
        super();
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
        this.sequence = values;
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

    @Override
    public void addTo(Circuit circuit) {
        System.out.println("CALL " + name + ".addTo(circuit)");
        circuit.add(this);
        System.out.println("RETURN");
    }
}
