package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Jelforr�s t�pus� komponenst reprezent�l. Ezt kell implement�lnia a jelforr�soknak.
 *
 * @author balint
 */
public abstract class SourceComponent extends AbstractComponent {

    public SourceComponent(String name) {
        super(name);
        inputs = new Wire[0];
        outputs = new Wire[1];
    }

    /**
     * Be�ll�tjuk a jelforr�s �rt�k�t. Kapcsol� eset�n csak 1 elem� t�mb
     * adhat� param�terk�nt!
     * 
     * @param values
     */
    public abstract void setValues(Value[] values);

    /**
     * Lek�rhetj�k a jelforr�s �rt�keit, hogy el tudjuk menteni.
     *
     * @return �rt�kek (kapcsol�nak egy elem�)
     */
    public abstract Value[] getValues();

    @Override
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
