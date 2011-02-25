package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.Component;
import hu.override.logsim.component.impl.Toggle;
import hu.override.logsim.controller.Simulation;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author balint
 */
public class GuiView extends JFrame implements View {

    private final JPanel top;
    private final JPanel bottom;
    private HashMap<Component, JLabel> displayMap = new HashMap<Component, JLabel>();
    private HashMap<Component, JButton> sourceMap = new HashMap<Component, JButton>();

    public GuiView() {
        setLayout(new BorderLayout());

        add(top = new JPanel(), BorderLayout.NORTH);
        add(bottom = new JPanel(), BorderLayout.SOUTH);
        top.setLayout(new FlowLayout());
        bottom.setLayout(new FlowLayout());
    }

    public void update(Circuit circuit) {
        for (Component c : circuit.getDisplays()) {
            displayMap.get(c).setText(String.format("%s = %s", c.getName(), c.getValue() == Value.TRUE ? "1" : "0"));
        }
        for (Component c : circuit.getSources()) {
            if (c instanceof Toggle) {
                sourceMap.get(c).setText(String.format("%s = %s", c.getName(), c.getValue() == Value.TRUE ? "1" : "0"));
            }
        }
    }

    public void setController(Simulation sim) {
        Circuit circuit = sim.getCircuit();

        JLabel label;
        for (Component c : circuit.getDisplays()) {
            displayMap.put(c, label = new JLabel("X"));
            bottom.add(label);
        }

        JButton btn;
        for (final Component c : circuit.getSources()) {
            if (c instanceof Toggle) {
                sourceMap.put(c, btn = new JButton());
                btn.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent ae) {
                        ((Toggle) c).toggle();
                    }
                });
                top.add(btn);
            }
        }


        pack();
        setVisible(true);
    }
}
