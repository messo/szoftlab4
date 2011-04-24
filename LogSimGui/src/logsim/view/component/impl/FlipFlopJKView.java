package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.FlipFlopJK;
import logsim.view.component.ComponentView;

/**
 * JK flip-flopot kirajzoló osztály
 * 
 */
public class FlipFlopJKView extends ComponentView {

    /**
     * Becsomagolt JK flip-flop
     */
    private FlipFlopJK jk;

    /**
     * Konstruktor
     * @param ag Megjelenítendõ JK flip-flop
     */
    public FlipFlopJKView(FlipFlopJK jk) {
        super(30, 46);
        this.jk = jk;
    }

    /**
     * JK flip-flopra kattintás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(jk);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 30, 46);
        g.drawString("JK", 10, 27);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 3;
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
