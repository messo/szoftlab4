package logsim.model.component.impl;

import logsim.log.Logger;
import logsim.model.Value;
import logsim.model.component.SourceComponent;

/**
 * Kapcsol� jelforr�s, melyet a felhaszn�l� szimul�ci� k�zben kapcsolgathat.
 *
 * @author balint
 */
public class Toggle extends SourceComponent {

    public Toggle(String name) {
        super(name);
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        //bemenet �rt�k�nek bek�r�se
        Value v = Logger.logAskValue(this, "�llapot");
        //kimenet be�ll�t�sa
        outputs[0].setValue(v);
    }

    /**
     * Lek�rj�k a kapcsol� �rt�k�t (1 elem� t�mb)
     */
    
    @Override
    public Value[] getValues() {

        //mivel nem t�roljuk az �rt�keket, ez�rt ha lek�rdezz�k �ket
        //be kell k�rni �ket
        Value[] values = new Value[1];
        values[0] = Logger.logAskValue(this,"�rt�k");
        
         
        return values;
    }
    
    /**
     * Kapcsol� �llapot�nak v�ltoztat�sa, csak 1 elem� t�mb�t kaphat param�ter�l.
     *
     * @param newValues
     */
    @Override
    public void setValues(Value[] newValues) {
        if (newValues.length != 1) {
            throw new IllegalArgumentException("Kapcsol�nak csak egy �rt�ke lehet!");
        }

        // ha �j �rt�ket kapott
        if (newValues[0] != outputs[0].getValue()) {
            // elmentj�k az �rt�ket
            outputs[0].setValue(newValues[0]);
        }
    }

    @Override
    public String getClassName() {
        return "Toggle";
    }
}
