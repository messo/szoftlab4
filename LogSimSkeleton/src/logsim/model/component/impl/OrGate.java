package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 * VAGY kapu, az �ramk�r egyik alapeleme. Bemeneteire k�t�tt komponensek
 * ki�rt�kel�s�t kezdem�nyezi, s a kapott �rt�kek logikai VAGY kapcsolat�t
 * val�s�tja meg, amit a kimenet�n kiad.
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
        //bemenetek lek�rdez�se
        for (int i = 0; i < inputs.length; i++) {
            evaluateInput(i);
        }

        //kimenet �rt�k�nek bek�r�se
        Value v = Logger.logAskValue(this, "mit adjunk a vezet�kre");

        //kimenet be�ll�t�sa
        outputs[0].setValue(v);
    }

    @Override
    public String getClassName() {
        return "OrGate";
    }
}
