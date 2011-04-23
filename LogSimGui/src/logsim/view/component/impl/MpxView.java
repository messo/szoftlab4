package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Mpx;
import logsim.view.component.ComponentView;

/**
 * Multiplexert kirajzoló osztály
 * 
 */
public class MpxView extends ComponentView {

    private Mpx mpx;

    /**
     * Konstruktor
     * @param mpx Megjelenítendõ multiplexer
     */
    public MpxView(Mpx mpx) {
        super(40, 62);
        this.mpx = mpx;
    }

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(mpx);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 62);
        g.drawString("MPX", 10, 35);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 6;
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
