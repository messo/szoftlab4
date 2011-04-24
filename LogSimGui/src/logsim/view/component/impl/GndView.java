package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * GND-t kirajzoló osztály
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
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("0", 3, 10);
        g.setColor(Color.BLACK);
    }

    /**
     * Bemeneti pinek száma
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
