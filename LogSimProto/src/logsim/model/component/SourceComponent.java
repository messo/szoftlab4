package logsim.model.component;

import logsim.Viewable;
import logsim.model.Value;

/**
 * Jelforrás típusú komponenst reprezentál. Tõle származnak a jelforrások (pl. toggle).
 */
public abstract class SourceComponent extends AbstractComponent {

    /**
     * Konstruktor. Nincs bemenete és egy kimenete van
     * @param name Komponens neve
     */
    protected SourceComponent(String name) {
        super(name, 0, 1);
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
     * Jelforrás nullázása
     */
    public abstract void reset();

    /**
     * Hozzáadás kompozithoz.
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }
}
