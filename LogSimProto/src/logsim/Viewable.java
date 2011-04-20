package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;

/**
 * A kimenet interf�sze.
 */
public interface Viewable {

    /**
     * Ki�runk egy komponenst (be �s kimenetek)
     * @param ac komponens
     */
    public void writeDetails(AbstractComponent ac);

    /**
     * Ki�runk egy scope-ot
     * @param scope oszcilloszk�p
     */
    public void writeScopeDetails(Scope scope);

    /**
     * Ki�runk egy jelgener�tort
     * @param sg jelgener�tor
     */
    public void writeSequenceGeneratorDetails(SequenceGenerator sg);

    /**
     * Ki�rjuk, hogy bet�lt�s sikeres
     */
    public void writeLoadSuccessful();

    /**
     * Ki�rjuk, hogy a bet�lt�s sikertelen
     */
    public void writeLoadFailed();

    /**
     * Ki�rjuk, hogy a config f�jl ment�s sikeres
     */
    public void writeSaveSuccessful();

    /**
     * Ki�rjuk, hogy a config f�jl sikertelen
     */
    public void writeSaveFailed();

    /**
     * Szekvenciagener�tor szekvenci�j�nak ki�r�sa
     * @param sg szekvenciagener�tor
     */
    public void writeSequenceGenerator(SequenceGenerator sg);

    /**
     * Ki�rjuk, hogy a szimul�ci� sikeres
     */
    public void writeSimulationSuccessful();

    /**
     * Ki�rjuk, hogy a szimul�ci� sikertelen
     */
    public void writeSimulationFailed();

    /**
     * Ki�rja a kapcsol� �llapot�t
     * @param toggle kapcsol�
     */
    public void writeToggleValue(Toggle toggle);

    /**
     * Ki�rja a jelgener�tor szekvenci�j�t
     * @param sg szekvenciagener�tor
     */
    public void writeSequenceGeneratorSequence(SequenceGenerator sg);

    /**
     * Ki�rja a jelgener�tor �ppen kiadott �rt�k�t
     * @param sg szekvenciagener�tor
     */
    public void writeSequenceGeneratorValue(SequenceGenerator sg);

    /**
     * Ki�rja a led �rt�k�t
     * @param led led
     */
    public void writeLedValue(Led led);

    /**
     * Ki�rja a 7-szegmentes kijelz� szegmenseit.
     * @param seg 7-szegmenses kijelz�
     */
    public void writeSevenSegmentDisplayValues(SevenSegmentDisplay seg);

    /**
     * Ki�rja a scope �ltal t�rolt �rt�keket
     * @param scope oszcilloszk�p
     */
    public void writeScopeValues(Scope scope);

    /**
     * �j sor a kimeneten
     */
    public void newline();
}
