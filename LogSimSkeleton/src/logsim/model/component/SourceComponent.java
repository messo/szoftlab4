package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Jelforrás típusú komponenst reprezentál. Ezt kell implementálnia a jelforrásoknak.
 * pl. toggle
 */
public abstract class SourceComponent extends AbstractComponent {

    /**
     * Konstruktor. Nincs bemenete és egy kimenete van
     * @param name Komponens neve
     */
    public SourceComponent(String name) {
        super(name,0,1);
    }

    /**
     * Beállítjuk a jelforrás értékét. Kapcsoló esetén csak 1 elemû tömb
     * adható paraméterként!
     * 
     * @param values Érték(ek)et tároló tömb
     */
    public abstract void setValues(Value[] values);

    /**
     * Lekérhetjük a jelforrás értékeit, hogy el tudjuk menteni.
     *
     * @return értékek (kapcsolónak egy elemû)
     */
    public abstract Value[] getValues();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
