package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * VAGY kapu, az áramkör egyik alapeleme. Bemenetein lévõ értékek logikai VAGY kapcsolatát
 * valósítja meg, amit a kimenetén kiad.
 */
public class OrGate extends AbstractComponent {

    /**
     * Konstruktor. 1 kimenete van
     * @param inputPinsCount Bemenetek száma
     * @param name Vagy kapu neve
     */
    public OrGate(int inputPinsCount, String name) {
        super(name, inputPinsCount, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // bemenetek lekérdezése
        for (int i = 0; i < inputs.length; i++) {
            getInput(i);
        }

        // kimenet értékének bekérése
        Value v = Logger.logAskValue(this, "mit adjunk a vezetékre");

        // kimenet beállítása
        outputs[0].setValue(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "OrGate";
    }
}
