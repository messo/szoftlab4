package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;

/**
 * Megjelenítõ típusú komponenst reprezentál. Tõle származnak a megjelenítõk (pl. led).
 */
public abstract class DisplayComponent extends AbstractComponent {

    /**
     * Konstruktor. Nem lesz kimenete.
     * 
     * @param name Komponens neve
     * @param inputCount Bemenetek száma
     */
    protected DisplayComponent(String name, int inputCount) {
        // kimenet nélküli komponens
        super(name, inputCount, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTo(Composite composite) {
        Logger.logCall(this, "addTo", composite);
        composite.add(this);
        Logger.logReturn();
    }
}
