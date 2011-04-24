package logsim.view.component.impl;

import java.awt.Graphics;
import java.awt.Point;
import logsim.Controller;
import logsim.model.component.impl.Node;
import logsim.view.component.ComponentView;

/**
 * Node-ot kirajzoló osztály.
 */
public class NodeView extends ComponentView {

    private static int width = 5;
    private static int height = 5;
    private Node node;

    /**
     * Konstruktor
     * @param node Megjelenítendõ Node
     */
    public NodeView(Node node) {
        super(width, height);
        this.node = node;
    }

    /**
     * Komponensre kapcsolás
     * @param controller
     */
    @Override
    public void onClick(Controller controller) {
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.fillOval(0, 0, width, height);
    }

    /**
     * Megadott bemeneti pin relatív pozicíóját adja vissza
     * @param pin Bemeneti pin száma
     * @return
     */
    @Override
    public Point getRelativeInputPinPosition(int pin) {
        return new Point(width / 2, width / 2);
    }

    /**
     * Megadott kimeneti pin relatív pozicíóját adja vissza
     * @param pin Kimeneti pin száma
     * @return
     */
    @Override
    public Point getRelativeOutputPinPosition(int pin) {
        return new Point(width / 2, width / 2);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    /**
     * Kimeneti pinek száma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return node.getOutputsCount();
    }

}
