package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.OrGate;
import logsim.view.component.ComponentView;

/**
 * VAGY kaput kirajzoló osztály
 * 
 */
public class OrGateView extends ComponentView {

    private OrGate og;

    /**
     * Konstruktor
     * @param og Megjelenítendõ VAGY kapu
     */
    public OrGateView(OrGate og) {
        super(35, 30);
        this.og = og;
    }

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(og);
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        //g.drawRect(0, 0, 40, 30);
        //g.drawString("OR", 13, 19);
        g.drawOval(-35, 0, 70, 30);
        g.drawOval(-7, 0, 14, 30);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return og.getInputsCount();
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
