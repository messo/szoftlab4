package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 * Jelforr�s t�pus� komponenst reprezent�l. Ezt kell implement�lnia a jelforr�soknak.
 *
 * @author balint
 */
public abstract class SourceComponent extends AbstractComponent {

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
        super.addTo(circuit);
        circuit.add(this);
    }
}
