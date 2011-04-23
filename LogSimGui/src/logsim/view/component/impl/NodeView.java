package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Node;
import logsim.view.component.ComponentView;

/**
 * LED-et kirajzoló osztály.
 */
public class NodeView extends ComponentView {

    private static int width = 5;
    private static int height = 5;
    private Node node;

    public NodeView(Node node) {
        super(width, height);
        this.node = node;
    }

    @Override
    public void onClick(Controller controller) {
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(0, 0, width, height);
    }

    @Override
    public Point getRelativeInputPinPosition(int pin) {
        return new Point(width / 2, width / 2);
    }

    @Override
    public Point getRelativeOutputPinPosition(int pin) {
        return new Point(width / 2, width / 2);
    }

    @Override
    public void onDraw(Graphics g) {
    }

    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    @Override
    protected int getOutputPinsCount() {
        return node.getOutputsCount();
    }
}
