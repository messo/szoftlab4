package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.FlipFlopD;
import logsim.view.component.ComponentView;

/**
 * D flip-flopot kirajzol� oszt�ly
 *
 */
public class FlipFlopDView extends ComponentView {

    /**
     * Becsomagolt D flip-flop
     */
    private FlipFlopD d;

    /**
     * Konstruktor
     * @param ag Megjelen�tend� JK flip-flop
     */
    public FlipFlopDView(FlipFlopD d) {
        super(30, 46);
        this.d = d;
    }

    /**
     * D flip-flopra kattint�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(d);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 30, 46);
        g.drawString("D", 10, 27);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 2;
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
