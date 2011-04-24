package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.DisplayComponent;
import logsim.view.component.ComponentView;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 */
public class Led extends DisplayComponent {

    /**
     * Konstruktor. 1 bemenetû megjelenítõ
     * @param name Led neve
     */
    public Led(String name) {
        super(name, 1);
    }

    /**
     * Visszaadja a led értékét
     * @return Érték
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
