package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * VCC-t kirajzoló osztály
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
    public void onDraw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.drawString("1", 3, 10);
        g.setColor(Color.BLACK);
    }

    /**
     * Bemneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 0;
    }

    /**
     * Kimeneti pinek száma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
