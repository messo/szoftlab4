package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.FlipFlopJK;
import logsim.view.component.ComponentView;

/**
 * JK flip-flopot kirajzol� oszt�ly
 * 
 */
public class FlipFlopJKView extends ComponentView {

    /**
     * Becsomagolt JK flip-flop
     */
    private FlipFlopJK jk;

    /**
     * Konstruktor
     * @param ag Megjelen�tend� JK flip-flop
     */
    public FlipFlopJKView(FlipFlopJK jk) {
        super(30, 46);
        this.jk = jk;
    }

    /**
     * JK flip-flopra kattint�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(jk);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 30, 46);
        g.drawString("JK", 10, 27);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 3;
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
