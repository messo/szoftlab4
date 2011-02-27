package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;

/**
 * 7-szegmenses kijelzõt reprezentál, melyen 7 bemenete vezérli a megfelelõ szegmenseket.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends AbstractComponent implements IsDisplay {

    public SevenSegmentDisplay() {
        lastValue = new Value[7];
        currentValue = new Value[7];
        for (int i = 0; i < 7; i++) {
            lastValue[i] = Value.FALSE; // alapból innen indulunk.
        }
    }

    @Override
    public String toString() {
        return String.format("7SEG(%s): %s,%s,%s,%s,%s,%s,%s", name,
                lastValue[0], lastValue[1], lastValue[2], lastValue[3], lastValue[4],
                lastValue[5], lastValue[6]);
    }

    @Override
    protected void onEvaluation() {
        // a 7 bemeneten érkezõ jelet elmentjük.
        for (int i = 0; i < 7; i++) {
            currentValue[i] = evaluateInput(i);
        }
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 7;
    }
}
