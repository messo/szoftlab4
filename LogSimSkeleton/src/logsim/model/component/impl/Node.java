package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Wire;

/**
 *
 * @author Gabor
 */
public class Node extends AbstractComponent {

    public Node(int outputPinsCount, String name) {
        super(name);
        outputs = new Wire[outputPinsCount];
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        //bemenetén lévõ adat lekérése
        inputs[0].getValue();

        //új adat bekérése
        Value v2 = Logger.logAskValue(this, "mit adjunk a vezetékre");

        //kimenetekre az új érték kiírása
        //szerintem ide elég egyszer beírni az értéket, nem kell minden vezetékre külön-külön
        //mert a normális node-nál úgyis mindig ugyanaz lesz a kimeneti érték, mint a bemenetei mindenhol
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].setValue(v2);
        }
    }

    @Override
    public String getClassName() {
        return "Node";
    }
}
