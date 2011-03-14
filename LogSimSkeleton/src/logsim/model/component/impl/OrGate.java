package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * VAGY kapu, az áramkör egyik alapeleme. Bemeneteire kötött komponensek
 * kiértékelését kezdeményezi, s a kapott értékek logikai VAGY kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
 *
 * @author balint
 */
public class OrGate extends AbstractComponent {

    public OrGate(int inputPinsCount, String name) {
        super(name);
        inputs = new Wire[inputPinsCount];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        for (int i = 0; i < inputs.length; i++) {
            evaluateInput(i);
        }

        Value v = Logger.logAskValue(this, "mit adjunk a vezetékre");
        outputs[0].setValue(v);
    }

    @Override
    public String getClassName() {
        return "OrGate";
    }
}
