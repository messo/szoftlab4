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
        //+14 annyit tesz, hogy a sz�veg, a t�glalap sz�leit�l legal�bb 7 pixel t�vols�gra lesz x tengelyen
        //minden karakterre 6 pixelt sz�molva
        //�sszesen a t�glalap sz�less�ge a string hossza *6 pixel + a k�t beh�z�s(14 pixel)
        super(c.getName().length()*6+14,30);
        this.c = c;
    }

    /**
     * Kiraqjzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, c.getName().length()*6+14, 30);
        //a string kezd�poz�ci�ja a t�glalap sz�less�g�nek a fel�t�l balra tal�lhat�,
        //a string hossz�ra sz�m�tott pixel�rt�k fel�vel
        g.drawString(c.getName(),(c.getName().length()*6+14)/2-c.getName().length()*6/2, 20);
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
