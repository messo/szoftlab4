package logsim.model.component.impl;

import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.DisplayComponent;

/**
 * 7-szegmenses kijelzõt reprezentál, melynek 7 bemenete vezérli a
 * megfelelõ szegmenseket, ezek világítanak, ha az adott bemenetre logikai
 * igaz van kötve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    public SevenSegmentDisplay(String name) {
        super(name, 7);
    }

    /**
     * Egy szegmens értékének lekérdezése
     * 
     * @param segment melyik szegmens
     * @return szegmens értéke
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
