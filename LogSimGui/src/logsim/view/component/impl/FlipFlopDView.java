package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.FlipFlopD;
import logsim.view.component.ComponentView;

/**
 * D flip-flopot kirajzoló osztály
 *
 */
public class FlipFlopDView extends ComponentView {

    /**
     * Becsomagolt D flip-flop
     */
    private FlipFlopD d;

    /**
     * Konstruktor
     * @param ag Megjelenítendõ JK flip-flop
     */
    public FlipFlopDView(FlipFlopD d) {
        super(30, 46);
        this.d = d;
    }

    /**
     * D flip-flopra kattintás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(d);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 30, 46);
        g.drawString("D", 10, 27);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 2;
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
