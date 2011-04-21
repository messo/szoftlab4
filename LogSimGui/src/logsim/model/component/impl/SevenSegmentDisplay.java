package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.DisplayComponent;

/**
 * 7-szegmenses kijelz�t reprezent�l, melynek 7 bemenete vez�rli a
 * megfelel� szegmenseket, ezek vil�g�tanak, ha az adott bemenetre logikai
 * igaz van k�tve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    public SevenSegmentDisplay(String name) {
        super(name, 7);
    }

    /**
     * Egy szegmens �rt�k�nek lek�rdez�se
     * 
     * @param segment melyik szegmens
     * @return szegmens �rt�ke
     */
    public Value getSegment(int segment) {
        return getInput(segment);
    }

    @Override
    protected void onEvaluation() {
        // nop.
    }

    @Override
    public AbstractComponent copy(String newName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
