package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.component.AbstractComponent;
import logsim.view.component.impl.InverterView;

/**
 * Inverter alkatr�sz, mely invert�lva adja ki a kimenet�n a bemenet�n
 * �rkez� jelet.
 */
public class Inverter extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenet �s 1 kimenet
     * @param name Inverter neve
     */
    public Inverter(String name) {
        super(name, 1, 1);
    }

    @Override
    protected void onEvaluation() {
        outputs[0].setValue(getInput(1).invert());
    }

    @Override
    public AbstractComponent copy(String name) {
        return new Inverter(name);
    }

    @Override
    public InverterView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
