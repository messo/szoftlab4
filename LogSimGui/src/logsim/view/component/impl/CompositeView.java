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
        //+14 annyit tesz, hogy a szöveg, a téglalap széleitõl legalább 7 pixel távolságra lesz x tengelyen
        //minden karakterre 6 pixelt számolva
        //összesen a téglalap szélessége a string hossza *6 pixel + a két behúzás(14 pixel)
        super(c.getName().length()*6+14,30);
        this.c = c;
    }

    /**
     * Kiraqjzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, c.getName().length()*6+14, 30);
        //a string kezdõpozíciója a téglalap szélességének a felétõl balra található,
        //a string hosszára számított pixelérték felével
        g.drawString(c.getName(),(c.getName().length()*6+14)/2-c.getName().length()*6/2, 20);
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
