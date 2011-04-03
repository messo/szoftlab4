package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;

/**
 * Csom�pont elem. Az egyetlen bemenet�re k�t�tt �rt�ket kiadja az �sszes kimeneti l�b�n.
 */
public class Node extends AbstractComponent {

    /**
     * Konstruktor. 1 bemenete van
     * @param outputPinsCount Kimenetek sz�ma
     * @param name Csom�pont neve
     */
    public Node(int outputPinsCount, String name) {
        super(name, 1, outputPinsCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // Bemenet�n l�v� adat lek�r�se
        getInput(0);

        // Bek�rj�k a felhaszn�l�t�l, hogy az egyes kimeneteken milyen �rt�k legyen
        for (int i = 0; i < outputs.length; i++) {
            Value v = Logger.logAskValue(this, String.format("mit adjunk a %d. vezet�kre", i));
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
