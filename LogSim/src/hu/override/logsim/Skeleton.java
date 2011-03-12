package hu.override.logsim;

import hu.override.logsim.component.Wire;
import hu.override.logsim.component.impl.Inverter;
import hu.override.logsim.component.impl.Led;
import hu.override.logsim.component.impl.Node;
import hu.override.logsim.component.impl.OrGate;
import hu.override.logsim.component.impl.Toggle;
import java.io.IOException;

/**
 *
 * @author Balint
 */
public class Skeleton {

    public static final int KAPCSOLO_LED = 1;
    public static final int KAPCSOLO_2x_VAGY_LED = 2;
    public static final int KAPCSOLO_INVERTER_LED = 3;
    public static final int INVERTER_VISSZAKOTVE_LED = 4;
    public static final int VAGY_VISSZAKOTVE_LED = 5;

    public Skeleton() {
        int use_case = 4;

        switch (use_case) {
            case KAPCSOLO_LED:
                testKapcsoloLed();
                break;
            case KAPCSOLO_2x_VAGY_LED:
                testKapcsolo2xVagy();
                break;
            case KAPCSOLO_INVERTER_LED:
                testKapcsoloInverterLed();
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
        System.out.println("# Inicializálás");
        Simulation simulation = new Simulation(null);
        Circuit circuit = new Circuit();
        simulation.setCircuit(circuit);

        System.out.println("CREATE Wire wire");
        Wire wire = new Wire();

        System.out.println("CREATE Toggle toggle");
        Toggle t = new Toggle();
        t.setName("toggle");
        System.out.println("CALL toggle.setOutput(0, wire)");
        t.setOutput(0, wire);
        System.out.println("RETURN");

        System.out.println("CREATE Led led");
        Led l = new Led();
        l.setName("led");
        System.out.println("CALL led.setInput(0, wire)");
        l.setInput(0, wire);

        t.addTo(circuit);
        l.addTo(circuit);

        // bekérjük a usertõl
        t.setValues(new Value[]{getToggle()});

        System.out.println("# Szimuláció");
        simulation.start();
    }

    private void testKapcsolo2xVagy() {
        System.out.println("# Inicializálás");
        Simulation simulation = new Simulation(null);
        Circuit circuit = new Circuit();
        simulation.setCircuit(circuit);

        Wire t1_to_and = new Wire();
        Wire t2_to_and = new Wire();
        Wire and_to_l = new Wire();

        Toggle t1 = new Toggle();
        t1.setName("toggle1");
        t1.setOutput(0, t1_to_and);

        Toggle t2 = new Toggle();
        t2.setName("toggle2");
        t2.setOutput(0, t2_to_and);

        OrGate a = new OrGate(2);
        a.setName("orgate");
        a.setInput(0, t1_to_and);
        a.setInput(1, t2_to_and);
        a.setOutput(0, and_to_l);

        Led l = new Led();
        l.setName("led");
        l.setInput(0, and_to_l);

        t1.addTo(circuit);
        t2.addTo(circuit);
        a.addTo(circuit);
        l.addTo(circuit);

        // bekérjük a usertõl
        t1.setValues(new Value[]{getToggle()});
        // bekérjük a usertõl
        t2.setValues(new Value[]{getToggle()});

        System.out.println("# Szimuláció");
        simulation.start();
    }

    private void testKapcsoloInverterLed() {
                Simulation simulation = new Simulation(null);
        Circuit circuit = new Circuit();
        simulation.setCircuit(circuit);

        Wire t_to_inv = new Wire();
        Wire inv_to_led = new Wire();

        Toggle t = new Toggle();
        t.setName("toggle");
        t.setOutput(0, t_to_inv);

        Inverter inv = new Inverter();
        inv.setName("inverter");
        inv.setInput(0, t_to_inv);
        inv.setOutput(0, inv_to_led);

        Led l = new Led();
        l.setName("led");
        l.setInput(0, inv_to_led);

        t.addTo(circuit);
        inv.addTo(circuit);
        l.addTo(circuit);

        // bekérjük a usertõl
        t.setValues(new Value[]{getToggle()});

        simulation.start();
    }

    private void testInverterVisszakotveLed() {
        Simulation simulation = new Simulation(null);
        Circuit circuit = new Circuit();
        simulation.setCircuit(circuit);
        Wire inv_to_node = new Wire();
        Wire inv_to_inv = new Wire();
        Wire node_to_led = new Wire();

        Inverter t = new Inverter();
        t.setName("inverter");
        t.setInput(0, inv_to_inv);
        t.setOutput(0, inv_to_node);

        Node n = new Node(2);
        n.setName("node");
        n.setInput(0, inv_to_node);
        n.setOutput(0, inv_to_inv);
        n.setOutput(1, node_to_led);

        Led l = new Led();
        l.setName("led");
        l.setInput(0, node_to_led);

        t.addTo(circuit);
        n.addTo(circuit);
        l.addTo(circuit);

        simulation.start();
    }

    private void testVagyVisszakotveLed() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }

    private Value getToggle() {
        System.out.println("Kapcsoló? [0/1]");
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
}
