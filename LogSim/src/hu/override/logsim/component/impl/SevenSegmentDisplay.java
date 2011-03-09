package hu.override.logsim.component.impl;

import hu.override.logsim.component.DisplayComponent;

/**
 * 7-szegmenses kijelz�t reprezent�l, melynek 7 bemenete vez�rli a
 * megfelel� szegmenseket, ezek vil�g�tanak, ha az adott bemenetre logikai
 * igaz van k�tve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    @Override
    public String toString() {
        return String.format("7SEG(%s): %s,%s,%s,%s,%s,%s,%s", name,
                outputs[0].getValue(), outputs[1].getValue(), outputs[2].getValue(),
                outputs[3].getValue(), outputs[4].getValue(), outputs[5].getValue(),
                outputs[6].getValue());
    }

    @Override
    protected void onEvaluation() {
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 7;
    }
}
