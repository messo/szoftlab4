package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Inverter;
import logsim.view.component.ComponentView;

/**
 * Invertert kirajzoló osztály
 * 
 */
public class InverterView extends ComponentView {

    private Inverter inv;

    /**
     * Konstruktor
     * @param inv Megjelenítendõ Inverter
     */
    public InverterView(Inverter inv) {
        super(40, 14);
        this.inv = inv;
    }

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    public void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 14);
        g.drawString("INV", 12, 12);
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
        return 1;
    }
}
