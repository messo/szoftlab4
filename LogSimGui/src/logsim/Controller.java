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
     * Kapcsol� v�ltoztat�sa
     * @param toggle
     */
    void onComponentClick(Toggle toggle);

    /**
     * Jelgener�tor megjelen�t�se �s konfigur�l�sa
     * @param sg
     */
    void onComponentClick(SequenceGenerator sg);

    /**
     * Scope megjelen�t�s (eddig elt�rolt �rt�kek)
     * @param scope
     */
    void onComponentClick(Scope scope);

    /**
     * �ltal�nos komponens inform�ci� megjelen�t�s (n�v, bemenet, kimenet)
     * @param ag
     */
    void onComponentClick(AbstractComponent ag);

    void onStep();
}
