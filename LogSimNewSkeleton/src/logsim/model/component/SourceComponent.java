package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Jelforr�s t�pus� komponenst reprezent�l. T�le sz�rmaznak a jelforr�sok (pl. toggle).
 */
public abstract class SourceComponent extends AbstractComponent {

    /**
     * Konstruktor. Nincs bemenete �s egy kimenete van
     * @param name Komponens neve
     */
    protected SourceComponent(String name) {
        super(name, 0, 1);
    }

    /**
     * Be�ll�tjuk a jelforr�s �rt�k�t. Kapcsol� eset�n csak 1 elem� t�mb
     * adhat� param�terk�nt!
     * 
     * @param values �rt�k(ek)et t�rol� t�mb
     */
    public abstract void setValues(Value[] values);

    /**
     * Lek�rhetj�k a jelforr�s �rt�keit, hogy el tudjuk menteni.
     *
     * @return �rt�kek (kapcsol�nak egy elem�)
     */
    public abstract Value[] getValues();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addTo(Composite composite) {
        Logger.logCall(this, "addTo", composite);
        composite.add(this);
        Logger.logReturn();
    }
}
