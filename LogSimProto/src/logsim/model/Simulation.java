package logsim.model;

/**
 * Egy szimul�ci�t reprezent�l� objektum.
 * Utas�tja az �ramk�rt t�bb ki�rt�kel�si ciklus lefuttat�s�hoz,
 * am�g az �ramk�rben van v�ltoz�s. Ha a v�ltoz�s megadott l�p�sen bel�l
 * nem �ll meg, t�j�koztatja a felhaszn�l�t, hogy nincs stacion�rius �llapot.
 */
public class Simulation {

    /**
     * Szimul�lt �ramk�r
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombin�ci�kra szimul�lja a h�l�zatot, am�g be nem �ll a
     * stacion�rius �llapot.
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
