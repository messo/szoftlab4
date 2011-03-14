package logsim;

import logsim.model.Simulation;
import logsim.model.skeleton.Circuit1;
import logsim.model.skeleton.Circuit2;
import logsim.model.skeleton.Circuit3;
import logsim.model.skeleton.Circuit4;
import logsim.model.skeleton.Circuit5;

/**
 *
 * @author Balint
 */
public class Skeleton {

    public static final int KAPCSOLO_LED = 1;
    public static final int KAPCSOLO_INVERTER_LED = 2;
    public static final int KAPCSOLO_2x_VAGY_LED = 3;
    public static final int INVERTER_VISSZAKOTVE_LED = 4;
    public static final int VAGY_VISSZAKOTVE_LED = 5;

    public Skeleton() {
        int use_case = 1;

        switch (use_case) {
            case KAPCSOLO_LED:
                testKapcsoloLed();
                break;
            case KAPCSOLO_INVERTER_LED:
                testKapcsoloInverterLed();
                break;
            case KAPCSOLO_2x_VAGY_LED:
                testKapcsolo2xVagy();
                break;
            case INVERTER_VISSZAKOTVE_LED:
                testInverterVisszakotveLed();
                break;
            case VAGY_VISSZAKOTVE_LED:
                testVagyVisszakotveLed();
                break;
        }
    }

    private void testKapcsoloLed() {
        Simulation simulation = new Simulation();
        Circuit1 c = new Circuit1();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testKapcsoloInverterLed() {
        Simulation simulation = new Simulation();
        Circuit2 c = new Circuit2();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testKapcsolo2xVagy() {
        Simulation simulation = new Simulation();
        Circuit3 c = new Circuit3();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testInverterVisszakotveLed() {
        Simulation simulation = new Simulation();
        Circuit4 c = new Circuit4();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testVagyVisszakotveLed() {
        Simulation simulation = new Simulation();
        Circuit5 c = new Circuit5();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }
}
