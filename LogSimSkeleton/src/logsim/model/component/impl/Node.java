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
        //bemenet�n l�v� adat lek�r�se
        inputs[0].getValue();

        //�j adat bek�r�se
        Value v2 = Logger.logAskValue(this, "mit adjunk a vezet�kre");

        //kimenetekre az �j �rt�k ki�r�sa
        //szerintem ide el�g egyszer be�rni az �rt�ket, nem kell minden vezet�kre k�l�n-k�l�n
        //mert a norm�lis node-n�l �gyis mindig ugyanaz lesz a kimeneti �rt�k, mint a bemenetei mindenhol
        for (int i = 0; i < outputs.length; i++) {
            outputs[i].setValue(v2);
        }
    }

    @Override
    public String getClassName() {
        return "Node";
    }
}
