package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.IsDisplay;
import hu.override.logsim.component.IsSource;
import hu.override.logsim.component.impl.Toggle;
import hu.override.logsim.controller.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private final JPanel center;
    private final JPanel bottom;
    private HashMap<IsDisplay, JLabel> displayMap = new HashMap<IsDisplay, JLabel>();
    private HashMap<IsSource, JButton> sourceMap = new HashMap<IsSource, JButton>();
    private final Controller controller;

    public GuiView(final Controller controller) {
        super("LogSim v0.1");

        this.controller = controller;

        setLayout(new BorderLayout());

        add(top = new JPanel(), BorderLayout.NORTH);
        add(center = new JPanel(), BorderLayout.CENTER);
        add(bottom = new JPanel(), BorderLayout.SOUTH);
        top.setLayout(new FlowLayout());
        center.setLayout(new FlowLayout());
        bottom.setLayout(new FlowLayout());

        final JButton start = new JButton("Step");

        top.add(start);
        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                controller.onStart();
            }
        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                controller.onExit();
            }
        });
    }

    @Override
    public void update(Circuit circuit) {
        for (IsDisplay c : circuit.getDisplays()) {
            displayMap.get(c).setText(c.toString());
        }
        for (IsSource c : circuit.getSources()) {
            if (c instanceof Toggle) {
                sourceMap.get(c).setText(String.format("%s = %s", c.getName(), c.getValue() == Value.TRUE ? "1" : "0"));
            }
        }
    }

    public void layoutDone() {
        pack();
        setVisible(true);
    }

    public void addSource(final IsSource source) {
        if (source instanceof Toggle) {
            JButton btn = new JButton(String.format("%s = %s", source.getName(), "X"));
            sourceMap.put(source, btn);
            btn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    ((Toggle) source).toggle();
                }
            });
            center.add(btn);
        }
    }

    public void addDisplay(final IsDisplay display) {
        JLabel label = new JLabel("X");
        displayMap.put(display, label);
        bottom.add(label);
    }
}
