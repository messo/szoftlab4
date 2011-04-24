package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * GND-t kirajzol� oszt�ly
 */
public class GndView extends ComponentView {

    private static int width = 10;
    private static int height = 11;

    /**
     * Konstruktor
     */
    public GndView() {
        super(width, height);
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("0", 3, 10);
        g.setColor(Color.BLACK);
    }

    /**
     * Bemeneti pinek sz�ma
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
