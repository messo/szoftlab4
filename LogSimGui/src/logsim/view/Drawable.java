package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * �ramk�ri panelre rajzolhat� objektum.
 */
public interface Drawable {

    /**
     * Itt �rjuk le, hogy pontosan mit kell kirajzolni.
     * 
     * @param g
     */
    void draw(Graphics g);

    /**
     * Lek�rhetj�k az objektumt�l a m�ret�t.
     * 
     * @return
     */
    Dimension getDimension();

    void onClick(Controller controller);
}
