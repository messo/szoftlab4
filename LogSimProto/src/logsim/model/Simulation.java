package logsim.model;

/**
 * Egy szimulációt reprezentáló objektum.
 * Utasítja az áramkört több kiértékelési ciklus lefuttatásához,
 * amíg az áramkörben van változás. Ha a változás megadott lépésen belül
 * nem áll meg, tájékoztatja a felhasználót, hogy nincs stacionárius állapot.
 */
public class Simulation {

    /**
     * Szimulált áramkör
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombinációkra szimulálja a hálózatot, amíg be nem áll a
     * stacionárius állapot.
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
