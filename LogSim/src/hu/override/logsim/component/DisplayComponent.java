package hu.override.logsim.component;

import hu.override.logsim.Circuit;

/**
 * Megjelenítõ típusú komponenst reprezentál. Ezt kell implementálnia a megjelenítõknek.
 *
 * @author balint
 */
public abstract class DisplayComponent extends AbstractComponent {

    @Override
    public void addTo(Circuit circuit) {
        super.addTo(circuit);
        circuit.add(this);
    }
}
