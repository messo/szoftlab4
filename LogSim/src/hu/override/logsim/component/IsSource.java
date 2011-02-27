package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 * Jelforrás típusú komponenst reprezentál. Ezt kell implementálnia a jelforrásoknak.
 *
 * @author balint
 */
public interface IsSource extends Component {

    /**
     * Beállítjuk a jelforrás értékét. Kapcsoló esetén csak 1 elemû tömb
     * adható paraméterként!
     * 
     * @param values
     */
    void setValues(Value[] values);

    /**
     * Lekérjük a jelforrás értékeit, hogy el tudjuk menteni.
     *
     * @return
     */
    Value[] getValues();
}
