package logsim.model.component;

import logsim.Viewable;

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
        composite.add(this);
    }
}
