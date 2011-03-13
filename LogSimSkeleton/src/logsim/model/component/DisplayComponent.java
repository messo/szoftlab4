package logsim.model.component;

import logsim.log.Logger;
import logsim.model.Circuit;

/**
 * Megjelen�t� t�pus� komponenst reprezent�l. Ezt kell implement�lnia a megjelen�t�knek.
 *
 * @author balint
 */
public abstract class DisplayComponent extends AbstractComponent {

    public DisplayComponent(String name) {
        super(name);
        outputs = new Wire[0];
    }

    @Override
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
