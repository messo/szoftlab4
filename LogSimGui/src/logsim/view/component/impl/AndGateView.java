package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.AndGate;
import logsim.view.component.ComponentView;

/**
 * ÉS kaput kirajzoló osztály
 * 
 */
public class AndGateView extends ComponentView {

    /**
     * Becsomagolt ÉS kapu
     */
    private AndGate ag;

    /**
     * Konstruktor
     * @param ag Megjelenítendõ ÉS kapu
     */
    public AndGateView(AndGate ag) {
        super(35, 30);
        this.ag = ag;
    }

    /**
     * ÉS kapura kattintás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(ag);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        //g.drawRect(0, 0, 40, 30);
        //g.drawString("AND", 10, 19);
        g.drawLine(0, 0, 0, 30);
        g.drawLine(0, 0, 20, 0);
        g.drawLine(0, 30, 20, 30);
        g.drawArc(5, 0, 30, 30, 0, 90);
        g.drawArc(5, 0, 30, 30, 0, -90);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return ag.getInputsCount();
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
