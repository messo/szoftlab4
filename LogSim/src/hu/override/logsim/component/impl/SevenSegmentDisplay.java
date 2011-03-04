package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.DisplayComponent;

/**
 * 7-szegmenses kijelz�t reprezent�l, melynek 7 bemenete vez�rli a
 * megfelel� szegmenseket, ezek vil�g�tanak, ha az adott bemenetre logikai
 * igaz van k�tve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    public SevenSegmentDisplay() {
        values = new Value[7];
        for (int i = 0; i < 7; i++) {
            values[i] = Value.FALSE; // alapb�l innen indulunk.
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

        // a 7 bemeneten �rkez� jelet elmentj�k.
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
