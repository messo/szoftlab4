package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

/**
 * A program ezeketer a szolg�ltat�sokat ny�jta a grafikus fel�let fel�
 */
public interface Controller {

    /**
     * �ramk�r bet�lt�se
     * @param fileName �ramk�rt le�r� f�jl neve
     */
    void loadCircuit(String fileName);

    /**
     * �romk�r konfigur�ci�s f�jl bet�lt�se
     * @param fileName Konfigur�ci�t t�rol� f�jl neve
     */
    void loadConfiguration(String fileName);

    /**
     * Konfigur�ci�s f�jl ment�se
     * @param fileName F�jl neve
     */
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

    /**
     * �ramk�r l�ptet�se
     */
    void onStep();

    /**
     * Szimul�ci� sebess�g�nek megv�ltoztat�sa
     */
    void onPeriodChanged(int p);

    /**
     * �j szekvencia ment�se
     */
    void onSequenceChanged(SequenceGenerator sg, String seq);
}
