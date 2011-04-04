package logsim.model.component.impl;

import logsim.model.component.AbstractComponent;

/**
 * Inverter alkatrész, mely invertálva adja ki a kimenetén a bemenetén
 * érkezõ jelet.
 */
public class Inverter extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenet és 1 kimenet
     * @param name Inverter neve
     */
    public Inverter(String name) {
        super(name, 1, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        outputs[0].setValue(getInput(1).invert());
    }

    @Override
    public AbstractComponent copy(String name) {
        return new Inverter(name);
    }
}
