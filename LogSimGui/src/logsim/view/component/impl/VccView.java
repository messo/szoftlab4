package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * VCC-t kirajzol� oszt�ly
 */
public class VccView extends ComponentView {

    private static int width = 10;
    private static int height = 11;

    /**
     * Konstruktor
     */
    public VccView() {
        super(width, height);
    }

    /**
     * Komponensre kapcsol�s
     * @param controller
     */
    @Override
    public void onClick(Controller controller) {
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.drawString("1", 3, 10);
        g.setColor(Color.BLACK);
    }

    /**
     * Bemneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 0;
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
