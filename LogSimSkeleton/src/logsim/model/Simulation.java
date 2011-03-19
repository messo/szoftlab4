package logsim.model;

import logsim.log.Loggable;
import logsim.log.Logger;

/**
 * Egy szimul�ci�t reprezent�l� objektum.
 * Utas�tja az �ramk�rt t�bb ki�rt�kel�si ciklus lefuttat�s�hoz,
 * am�g az �ramk�rben van v�ltoz�s. Ha a v�ltoz�s megadott l�p�sen bel�l
 * nem �ll meg, t�j�koztatja a felhaszn�l�t, hogy nincs stacion�rius �llapot.
 */
public class Simulation implements Loggable {

    /**
     * Szimul�lt �ramk�r
     */
    protected Circuit circuit;

    /**
     * Egy adott bemeneti kombin�ci�kra szimul�lja a h�l�zatot, am�g be nem �ll a
     * stacion�rius �llapot.
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
     * Szimul�lt �ramk�r be�ll�t�sa
     * 
     * @param circuit Szimul�lni k�v�nt �ramk�r
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
