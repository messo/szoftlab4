package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;
import logsim.view.component.ComponentView;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha bemenet�n igaz �rt�k van.
 */
public class Led extends DisplayComponent {

    /**
     * Konstruktor. 1 bemenet� megjelen�t�
     * @param name Led neve
     */
    public Led(String name) {
        super(name, 1);
    }

    /**
     * Visszaadja a led �rt�k�t
     * @return �rt�k
     */
    public Value getValue() {
        return getInput(1);
    }

    @Override
    protected void onEvaluation() {
        // nop.
    }

    @Override
    public Led copy(String name) {
        return new Led(name);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
