package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;
import logsim.model.component.Wire;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
 * 3 f�le sz�ne lehet, ezeket a Color enumer�ci� hat�rozza meg.
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
        //bemenet�n l�v� adat lek�r�se
        Value v = inputs[0].getValue();

        //led �llapot�nak ki�r�sa
        Logger.logComment((v == Value.TRUE) ? "vil�g�t" : "nem vil�g�t");
    }

    @Override
    public String getClassName() {
        return "Led";
    }
}
