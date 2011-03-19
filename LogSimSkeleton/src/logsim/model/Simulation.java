package logsim.model;

import logsim.log.Loggable;
import logsim.log.Logger;

/**
 * Egy szimulációt reprezentáló objektum.
 * Utasítja az áramkört több kiértékelési ciklus lefuttatásához,
 * amíg az áramkörben van változás. Ha a változás megadott lépésen belül
 * nem áll meg, tájékoztatja a felhasználót, hogy nincs stacionárius állapot.
 */
public class Simulation implements Loggable {

    /**
     * Szimulált áramkör
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombinációkra szimulálja a hálózatot, amíg be nem áll a
     * stacionárius állapot.
     */
    public boolean start() {
        Logger.logCall(this, "start");
        int counter = 0;
        while (counter < 2) {
            circuit.doEvaluationCycle();
            if (!circuit.isChanged()) {
                break;
            }
            counter++;
        }
        if (counter == 2) {
            Logger.logReturn("false");
            return false;
        }
        circuit.commitFlipFlops();
        circuit.stepGenerators();

        Logger.logReturn("true");
        return true;
    }

    /**
     * Szimulált áramkör beállítása
     * 
     * @param circuit Szimulálni kívánt áramkör
     */
    public void setCircuit(Circuit circuit) {
        Logger.logCall(this, "setCircuit", circuit);
        this.circuit = circuit;
        Logger.logReturn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "simulation";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Simulation";
    }
}
