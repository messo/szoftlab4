package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Csomópont elem. Az egyetlen bemenetére kötött értéket kiadja az összes kimeneti lábán.
 */
public class Node extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenete van
     * @param outputPinsCount Kimenetek száma
     * @param name Csomópont neve
     */
    public Node(int outputPinsCount, String name) {
        super(name, 1, outputPinsCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // Bemenetén lévõ adat lekérése
        getInput(0);

        // Bekérjük a felhasználótól, hogy az egyes kimeneteken milyen érték legyen
        for (int i = 0; i < outputs.length; i++) {
            Value v = Logger.logAskValue(this, String.format("mit adjunk a %d. vezetékre", i));
            outputs[i].setValue(v);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Node";
    }
}
