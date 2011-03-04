package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 * Jelforrás típusú komponenst reprezentál. Ezt kell implementálnia a jelforrásoknak.
 *
 * @author balint
 */
public abstract class SourceComponent extends AbstractComponent {

    /**
     * Beállítjuk a jelforrás értékét. Kapcsoló esetén csak 1 elemû tömb
     * adható paraméterként!
     * 
     * @param values
     */
    public abstract void setValues(Value[] values);

    /**
     * Lekérhetjük a jelforrás értékeit, hogy el tudjuk menteni.
     *
     * @return értékek (kapcsolónak egy elemû)
     */
    public abstract Value[] getValues();

    @Override
    public void addTo(Circuit circuit) {
        super.addTo(circuit);
        circuit.add(this);
    }
}
