package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 * Jelforr�s t�pus� komponenst reprezent�l. Ezt kell implement�lnia a jelforr�soknak.
 *
 * @author balint
 */
public interface IsSource extends Component {

    /**
     * Be�ll�tjuk a jelforr�s �rt�k�t. Kapcsol� eset�n csak 1 elem� t�mb
     * adhat� param�terk�nt!
     * 
     * @param values
     */
    void setValues(Value[] values);

    /**
     * Lek�rj�k a jelforr�s �rt�keit, hogy el tudjuk menteni.
     *
     * @return
     */
    Value[] getValues();
}
