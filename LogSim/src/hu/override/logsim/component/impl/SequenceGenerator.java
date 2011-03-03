package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.FlipFlop;
import hu.override.logsim.component.IsSource;
import java.util.ArrayList;
import java.util.List;

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
public class SequenceGenerator extends AbstractComponent implements IsSource {

    /**
     * Tárolt bitsorozat
     */
    private Value[] sequence;
    /**
     * Bitsorozat egy indexe, ez határozza meg, hogy éppen melyik értéket adja ki.
     */
    private int index;
    /**
     * Azon FF-ek listája, melyekre ez a jelgenerátor van bekötve a CLK bemenetre.
     */
    private List<FlipFlop> ffList = new ArrayList<FlipFlop>();

    /**
     * Konstruktor, ami alapállapotban a 0,1-es szekvenciát állítja be.
     */
    public SequenceGenerator() {
        sequence = new Value[2];
        sequence[0] = Value.FALSE;
        sequence[1] = Value.TRUE;
    }

    /**
     * A jelgenerátor lép, a bitsorozat következõ elemére ugrik. A következõ léptetésig
     * ez kerül kiadásra a kimeneteken.
     */
    public void step() {
        Value prev = sequence[index];
        index = (index + 1) % sequence.length;
        Value current = sequence[index];

        // a feliratkozott ff-ek aktívak lesznek, ha felfutó él, különben nem
        for (FlipFlop ff : ffList) {
            ff.setActive(current == Value.TRUE && prev == Value.FALSE);
        }
    }

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        result[0] = sequence[index];
        return result;
    }

    /**
     * Jelgenerátor bitsorozatának beállítása
     *
     * @return
     */
    @Override
    public void setValues(Value[] values) {
        this.sequence = values;
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
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 0;
    }

    /**
     * A flipflop-ot feliratkoztatjuk a jelgenerátorhoz, így ha felfutó él lesz,
     * akkor tudunk neki jelezni.
     *
     * @param ff feliratkozandó ff
     */
    public void addFlipFlop(FlipFlop ff) {
        ffList.add(ff);
    }
}
