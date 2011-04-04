package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;

/**
 * A kimenet interfésze.
 */
public interface Viewable {

    /**
     * Kiírunk egy komponenst (be és kimenetek)
     * @param ac komponens
     */
    public void writeDetails(AbstractComponent ac);

    /**
     * Kiírunk egy scope-ot
     * @param scope oszcilloszkóp
     */
    public void writeScopeDetails(Scope scope);

    /**
     * Kiírjuk, hogy betöltés sikeres
     */
    public void writeLoadSuccessful();

    /**
     * Kiírjuk, hogy a betöltés sikertelen
     */
    public void writeLoadFailed();

    /**
     * Kiírjuk, hogy a config fájl mentés sikeres
     */
    public void writeSaveSuccessful();

    /**
     * Kiírjuk, hogy a config fájl sikertelen
     */
    public void writeSaveFailed();

    /**
     * Szekvenciagenerátor szekvenciájának kiírása
     * @param sg szekvenciagenerátor
     */
    public void writeSequenceGenerator(SequenceGenerator sg);

    /**
     * Kiírjuk, hogy a szimuláció sikeres
     */
    public void writeSimulationSuccessful();

    /**
     * Kiírjuk, hogy a szimuláció sikertelen
     */
    public void writeSimulationFailed();

    /**
     * Kiírja a kapcsoló állapotát
     * @param toggle
     */
    public void writeToggleValue(Toggle toggle);

    /**
     * Kiírja a jelgenerátor szekvenciáját
     * @param sg
     */
    public void writeSequenceGeneratorSequence(SequenceGenerator sg);

    /**
     * Kiírja a jelgenerátor éppen kiadott értékét
     * @param sg
     */
    public void writeSequenceGeneratorValue(SequenceGenerator sg);

    /**
     * Kiírja a led értékét
     * @param led
     */
    public void writeLedValue(Led led);

    /**
     * Kiírja a 7-szegmentes kijelzõ szegmenseit.
     * @param seg
     */
    public void writeSevenSegmentDisplayValues(SevenSegmentDisplay seg);

    /**
     * Kiírja a scope által tárolt értékeket
     * @param scope
     */
    public void writeScopeValues(Scope scope);

    /**
     * Új sor a kimeneten
     */
    public void newline();
}
