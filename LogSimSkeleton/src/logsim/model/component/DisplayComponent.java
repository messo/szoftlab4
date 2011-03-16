package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;

/**
 * Megjelen�t� t�pus� komponenst reprezent�l. Ezt kell implement�lnia a megjelen�t�knek.
 * pl. led
 */
public abstract class DisplayComponent extends AbstractComponent {

    /**
     * Konstruktor
     * @param name Komponens neve. Nincs kimenete
     * @param inputCount Bemenetek sz�ma
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
