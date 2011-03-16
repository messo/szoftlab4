package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;

/**
 * Megjelenítõ típusú komponenst reprezentál. Ezt kell implementálnia a megjelenítõknek.
 * pl. led
 */
public abstract class DisplayComponent extends AbstractComponent {

    /**
     * Konstruktor
     * @param name Komponens neve. Nincs kimenete
     * @param inputCount Bemenetek száma
     */
    protected DisplayComponent(String name, int inputCount) {
        super(name, inputCount, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
