package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Led;
import logsim.view.component.ComponentView;

/**
 * LED-et kirajzoló osztály.
 */
public class LedView extends ComponentView {

    private static int width = 12;
    private static int height = 12;
    private Led led;

    /**
     * Konstruktor
     * @param led Megjelenítendõ LED
     */
    public LedView(Led led) {
        super(width, height);
        this.led = led;
    }

    /**
     * Komponensre kacsolás
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
        if (led.getValue() == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(0, 0, width, height);
            g.setColor(Color.BLACK);
        }
        g.drawOval(0, 0, width, height);
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
        return 0;
    }
}
