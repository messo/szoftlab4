package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Inverter;
import logsim.view.component.ComponentView;

/**
 * Invertert kirajzol� oszt�ly
 * 
 */
public class InverterView extends ComponentView {

    private Inverter inv;

    /**
     * Konstruktor
     * @param inv Megjelen�tend� Inverter
     */
    public InverterView(Inverter inv) {
        super(28, 20);
        this.inv = inv;
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawPolygon(new int[]{0, 20, 0}, new int[]{0, 10, 20}, 3);
        g.drawOval(20, 6, 8, 8);
        //g.drawRect(0, 0, 40, 14);
        //g.drawString("INV", 12, 12);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 1;
    }

//    @Override
//    protected void showDetails(DetailedView dv) {
//        dv.showDetails(inv);
//    }
}
