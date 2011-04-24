package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

/**
 * A program ezeketer a szolgáltatásokat nyújta a grafikus felület felé
 */
public interface Controller {

    /**
     * Áramkör betöltése
     * @param fileName Áramkört leíró fájl neve
     */
    void loadCircuit(String fileName);

    /**
     * Áromkör konfigurációs fájl betöltése
     * @param fileName Konfigurációt tároló fájl neve
     */
    void loadConfiguration(String fileName);

    /**
     * Konfigurációs fájl mentése
     * @param fileName Fájl neve
     */
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

    /**
     * Áramkör léptetése
     */
    void onStep();

    /**
     * Szimuláció sebességének megváltoztatása
     */
    void onPeriodChanged(int p);

    /**
     * Új szekvencia mentése
     */
    void onSequenceChanged(SequenceGenerator sg, String seq);
}
