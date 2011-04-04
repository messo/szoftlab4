package logsim.model;

/**
 * Egy szimulációt reprezentáló objektum.
 * Utasítja az áramkört, hogy értékelje ki magát. Ha az áramkör azt jelzi magáról,
 * hogy nincs stacionárius állapota akkor jelezzük a felhasználónak.
 */
public class Simulation {

    /**
     * Szimulált áramkör
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombinációkra kiértékeli a hálózatot.
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
     * Szimulált áramkör beállítása
     * 
     * @param circuit Szimulálni kívánt áramkör
     */
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
