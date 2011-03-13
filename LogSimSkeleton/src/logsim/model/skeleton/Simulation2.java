package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Simulation;

/**
 *
 * @author Balint
 */
public class Simulation2 extends Simulation {

    public Simulation2() {
        Logger.logComment("Inicializ�l�s");
        Circuit2 circuit = new Circuit2();
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
