package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.DisplayComponent;
import hu.override.logsim.component.Wire;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
 * 3 f�le sz�ne lehet, ezeket a Color enumer�ci� hat�rozza meg.
 *
 * @author balint
 */
public class Led extends DisplayComponent {

    public Led() {
        outputs = new Wire[0];
        inputs = new Wire[1];
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
        Value v = inputs[0].getValue();
        System.out.println("      CALL inputs[0].getValue()");
        System.out.println("      RETURN [" + v + "]");
        System.out.println("      # " + (v == Value.TRUE ? "vil�g�t" : "nem vil�g�t"));
    }
}
