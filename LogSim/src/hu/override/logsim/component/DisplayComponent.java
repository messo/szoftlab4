package hu.override.logsim.component;

import hu.override.logsim.Circuit;

/**
 * Megjelen�t� t�pus� komponenst reprezent�l. Ezt kell implement�lnia a megjelen�t�knek.
 *
 * @author balint
 */
public abstract class DisplayComponent extends AbstractComponent {

    @Override
    public void addTo(Circuit circuit) {
        System.out.println("CALL " + name + ".addTo(circuit)");
        circuit.add(this);
        System.out.println("RETURN");
    }
}
