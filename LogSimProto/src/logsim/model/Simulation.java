package logsim.model;

/**
 * Egy szimul�ci�t reprezent�l� objektum.
 * Utas�tja az �ramk�rt, hogy �rt�kelje ki mag�t. Ha az �ramk�r azt jelzi mag�r�l,
 * hogy nincs stacion�rius �llapota akkor jelezz�k a felhaszn�l�nak.
 */
public class Simulation {

    /**
     * Szimul�lt �ramk�r
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombin�ci�kra ki�rt�keli a h�l�zatot.
     */
    public boolean start() {
        try {
            circuit.evaluate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * Szimul�lt �ramk�r be�ll�t�sa
     * 
     * @param circuit Szimul�lni k�v�nt �ramk�r
     */
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
