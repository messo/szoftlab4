package hu.override.logsim.view;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
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
    private HashMap<AbstractComponent, JLabel> displayMap = new HashMap<AbstractComponent, JLabel>();
    private HashMap<AbstractComponent, JButton> sourceMap = new HashMap<AbstractComponent, JButton>();
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

        final JButton start = new JButton("Start");
        final JButton stop = new JButton("Stop");
        stop.setEnabled(false);

        top.add(start);
        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                controller.onStart();
                start.setEnabled(false);
                stop.setEnabled(true);
            }
        });
        top.add(stop);
        stop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                controller.onStop();
                start.setEnabled(true);
                stop.setEnabled(false);
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
        for (AbstractComponent c : circuit.getDisplays()) {
            displayMap.get(c).setText(c.toString());
        }
        for (AbstractComponent c : circuit.getSources()) {
            if (c instanceof Toggle) {
                sourceMap.get(c).setText(String.format("%s = %s", c.getName(), c.getValue() == Value.TRUE ? "1" : "0"));
            }
        }
    }

    public void layoutDone() {
        pack();
        setVisible(true);
    }

    public void addSource(final AbstractComponent source) {
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

    public void addDisplay(final AbstractComponent display) {
        JLabel label = new JLabel("X");
        displayMap.put(display, label);
        bottom.add(label);
    }
}
