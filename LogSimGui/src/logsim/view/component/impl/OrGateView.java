package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.OrGate;
import logsim.view.component.ComponentView;

/**
 * VAGY kaput kirajzol� oszt�ly
 * 
 */
public class OrGateView extends ComponentView {

    private OrGate og;

    /**
     * Konstruktor
     * @param og Megjelen�tend� VAGY kapu
     */
    public OrGateView(OrGate og) {
        super(40, 30);
        this.og = og;
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(og);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("OR", 13, 19);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return og.getInputsCount();
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
