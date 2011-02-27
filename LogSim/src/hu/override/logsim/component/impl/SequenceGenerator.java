package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.FlipFlop;
import hu.override.logsim.component.IsSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Jelgener�tort reprezent�l, amely a be�ll�tott bitsorozatot adja ki. A
 * SequenceGeneratorStepper feladata, hogy a step() met�dust megh�vja ezen oszt�ly
 * p�ld�nyain. Azokat a FF-eket vez�rli, melyek CLK bemenet�re ez a komponens van k�tve,
 * vagyis ha �ppen felfut� �l j�n, akkor ezeket enged�lyezi k�l�nben nem.
 *
 * @author balint
 */
public class SequenceGenerator extends AbstractComponent implements IsSource {

    /**
     * T�rolt bitsorozat
     */
    private Value[] sequence;
    /**
     * Bitsorozat egy indexe, ez hat�rozza meg, hogy �ppen melyik �rt�ket adja ki.
     */
    private int idx;
    /**
     * Azon FF-ek list�ja, melyekre ez a jelgener�tor van bek�tve a CLK bemenetre.
     */
    private List<FlipFlop> ffList = new ArrayList<FlipFlop>();

    /**
     * A jelgener�tor l�p, a bitsorozat k�vetkez� elem�re ugrik. A k�vetkez� l�ptet�sig
     * ez ker�l kiad�sra a kimeneteken.
     */
    public void step() {
        Value prev = sequence[idx];
        idx = (idx + 1) % sequence.length;
        Value current = sequence[idx];

        // a feliratkozott ff-ek akt�vak lesznek, ha felfut� �l, k�l�nben nem
        for (FlipFlop ff : ffList) {
            ff.setActive(current == Value.TRUE && prev == Value.FALSE);
        }
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = sequence[idx];
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
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount > 0;
    }

    /**
     * A flipflop-ot feliratkoztatjuk a jelgener�torhoz, �gy ha felfut� �l lesz,
     * akkor tudunk neki jelezni.
     *
     * @param ff feliratkozand� ff
     */
    public void addFlipFlop(FlipFlop ff) {
        ffList.add(ff);
    }
}
