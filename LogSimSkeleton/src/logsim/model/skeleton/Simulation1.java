package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Simulation;

/**
 *
 * @author Balint
 */
public class Simulation1 extends Simulation {

    public Simulation1() {
        Logger.logComment("Inicializ�l�s");
        Circuit1 circuit = new Circuit1();
        setCircuit(circuit);

        circuit.init();
    }

    @Override
    public boolean start() {
        Logger.logCall(this, "start");
        // 1 darab ciklus el�g
        circuit.doEvaluationCycle();
        circuit.isChanged();
        Logger.logReturn("true");
        return true;
    }
}
