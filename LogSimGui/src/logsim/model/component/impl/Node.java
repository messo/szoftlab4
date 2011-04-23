package logsim.model.component.impl;

import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.view.component.ComponentView;

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

    @Override
    protected void onEvaluation() {
        // Bemenetén lévõ adat lekérése
        Value v = getInput(1);

        // Bekérjük a felhasználótól, hogy az egyes kimeneteken milyen érték legyen
        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] != null) {
                outputs[i].setValue(v);
            }
        }
    }

    @Override
    public AbstractComponent copy(String name) {
        return new Node(outputs.length, name);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
