package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.AndGate;
import logsim.view.component.ComponentView;

/**
 * �S kaput kirajzol� oszt�ly
 * 
 */
public class AndGateView extends ComponentView {

    /**
     * Becsomagolt �S kapu
     */
    private AndGate ag;

    /**
     * Konstruktor
     * @param ag Megjelen�tend� �S kapu
     */
    public AndGateView(AndGate ag) {
        super(40, 30);
        this.ag = ag;
    }

    /**
     * �S kapura kattint�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(ag);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("AND", 10, 19);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return ag.getInputsCount();
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
