package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Simulation;

/**
 *
 * @author Balint
 */
public class Simulation3 extends Simulation {

    public Simulation3() {
        Logger.logComment("Inicializ�l�s");
        Circuit3 circuit = new Circuit3();
        setCircuit(circuit);

        circuit.init();
    }

    @Override
    public boolean start() {
        Logger.logCall(this, "start");
        // 2 darab ciklus el�g
        circuit.doEvaluationCycle();
        circuit.doEvaluationCycle();
        circuit.isChanged();
        Logger.logReturn("true");
        return true;
    }
}
