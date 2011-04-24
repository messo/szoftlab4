/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.Composite;
import logsim.view.component.ComponentView;

/**
 * Kompozitot kirajzoló osztály
 *
 */
public class CompositeView extends ComponentView {

    /**
     * Becsomagolt kompozit
     */
    private Composite c;

    /**
     * Konstruktor
     * @param c Megjelenítendõ kompozit
     */
    public CompositeView(Composite c){
        super(40,30);
        this.c = c;
        
    }

    /**
     * Kiraqjzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("COMP", 10, 27);
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return c.getInputsCount();
    }

    /**
     * Kimeneti pinek száma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return c.getOutputsCount();
    }

    /**
     * Komponensre kapcsolás
     * @param controller
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(c);
    }

}
