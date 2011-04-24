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
 * Kompozitot kirajzol� oszt�ly
 *
 */
public class CompositeView extends ComponentView {

    /**
     * Becsomagolt kompozit
     */
    private Composite c;

    /**
     * Konstruktor
     * @param c Megjelen�tend� kompozit
     */
    public CompositeView(Composite c){
        super(40,30);
        this.c = c;
        
    }

    /**
     * Kiraqjzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("COMP", 10, 27);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return c.getInputsCount();
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return c.getOutputsCount();
    }

    /**
     * Komponensre kapcsol�s
     * @param controller
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(c);
    }

}
