package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;
import logsim.model.component.Wire;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 * 3 féle színe lehet, ezeket a Color enumeráció határozza meg.
 *
 * @author balint
 */
public class Led extends DisplayComponent {

    public Led(String name) {
        super(name);
        inputs = new Wire[1];
        Logger.logReturn();
    }

    public Value getValue() {
        return inputs[0].getValue();
    }

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, inputs[0].getValue());
    }

    @Override
    protected void onEvaluation() {
        //bemenetén lévõ adat lekérése
        Value v = inputs[0].getValue();

        //led állapotának kiírása
        Logger.logComment((v == Value.TRUE) ? "világít" : "nem világít");
    }

    @Override
    public String getClassName() {
        return "Led";
    }
}
