package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

/**
 *
 */
public interface Controller {

    void loadCircuit(String fileName);

    void loadConfiguration(String fileName);

    void saveConfiguration(String fileName);

    /**
     * Kapcsoló változtatása
     * @param toggle
     */
    void onComponentClick(Toggle toggle);

    /**
     * Jelgenerátor megjelenítése és konfigurálása
     * @param sg
     */
    void onComponentClick(SequenceGenerator sg);

    /**
     * Scope megjelenítés (eddig eltárolt értékek)
     * @param scope
     */
    void onComponentClick(Scope scope);

    /**
     * Általános komponens információ megjelenítés (név, bemenet, kimenet)
     * @param ag
     */
    void onComponentClick(AbstractComponent ag);

    void onStep();
}
