package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Inverter;
import logsim.view.component.ComponentView;

/**
 * Invertert kirajzoló osztály
 * 
 */
public class InverterView extends ComponentView {

    private Inverter inv;

    /**
     * Konstruktor
     * @param inv Megjelenítendõ Inverter
     */
    public InverterView(Inverter inv) {
        super(28, 20);
        this.inv = inv;
    }

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    /**
     * Kirajzolási logika
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
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    /**
     * Kimeneti pinek száma
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
