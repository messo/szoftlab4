package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.DisplayComponent;

/**
 * 7-szegmenses kijelzõt reprezentál, melynek 7 bemenete vezérli a
 * megfelelõ szegmenseket, ezek világítanak, ha az adott bemenetre logikai
 * igaz van kötve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    public SevenSegmentDisplay() {
        values = new Value[7];
        for (int i = 0; i < 7; i++) {
            values[i] = Value.FALSE; // alapból innen indulunk.
        }
    }

    @Override
    public String toString() {
        return String.format("7SEG(%s): %s,%s,%s,%s,%s,%s,%s", name,
                values[0], values[1], values[2], values[3], values[4],
                values[5], values[6]);
    }

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        // a 7 bemeneten érkezõ jelet elmentjük.
        for (int i = 0; i < 7; i++) {
            result[i] = evaluateInput(i);
        }
        return result;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 7;
    }
}
