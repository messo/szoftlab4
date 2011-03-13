package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Jelforrás típusú komponenst reprezentál. Ezt kell implementálnia a jelforrásoknak.
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
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
