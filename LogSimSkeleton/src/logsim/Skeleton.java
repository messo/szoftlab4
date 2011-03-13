package logsim;

import logsim.model.Value;
import java.io.IOException;
import logsim.model.skeleton.Simulation1;
import logsim.model.skeleton.Simulation2;
import logsim.model.skeleton.Simulation3;

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
        int use_case = 3;

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
        Simulation1 simulation = new Simulation1();
        simulation.start();
    }

    private void testKapcsoloInverterLed() {
        Simulation2 simulation = new Simulation2();
        simulation.start();
    }

    private void testKapcsolo2xVagy() {
        Simulation3 simulation = new Simulation3();
        simulation.start();
    }

    private void testInverterVisszakotveLed() {
//        Simulation simulation = new Simulation();
//        Circuit circuit = new Circuit();
//        simulation.setCircuit(circuit);
//        Wire inv_to_node = new Wire();
//        Wire inv_to_inv = new Wire();
//        Wire node_to_led = new Wire();
//
//        Inverter t = new Inverter();
//        t.setName("inverter");
//        t.setInput(0, inv_to_inv);
//        t.setOutput(0, inv_to_node);
//
//        Node n = new Node(2);
//        n.setName("node");
//        n.setInput(0, inv_to_node);
//        n.setOutput(0, inv_to_inv);
//        n.setOutput(1, node_to_led);
//
//        Led l = new Led();
//        l.setName("led");
//        l.setInput(0, node_to_led);
//
//        t.addTo(circuit);
//        n.addTo(circuit);
//        l.addTo(circuit);
//
//        simulation.start();
    }

    private void testVagyVisszakotveLed() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Value getToggle() {
        System.out.println("Kapcsol?? [0/1]");
        int ch;
        try {
            while (true) {
                ch = System.in.read();
                if (ch == '0' || ch == '1') {
                    break;
                }
            }
            if (ch == '0') {
                return Value.FALSE;
            } else {
                return Value.TRUE;
            }
        } catch (IOException ex) {
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }
}
