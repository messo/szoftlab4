package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;
import logsim.model.component.Wire;

/**
 * 7-szegmenses kijelzõt reprezentál, melynek 7 bemenete vezérli a
 * megfelelõ szegmenseket, ezek világítanak, ha az adott bemenetre logikai
 * igaz van kötve.
 *
 * @author balint
 */
public class SevenSegmentDisplay extends DisplayComponent {

    public SevenSegmentDisplay(String name) {
        super(name);
        inputs = new Wire[7];
        Logger.logReturn();
    }

    public Value getSegment(int segment) {
        return inputs[segment].getValue();
    }

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
}
