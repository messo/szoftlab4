package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * �ramk�ri panelre rajzolhat� objektum.
 */
public interface Drawable {

    /**
     * Kirajzol�si logika
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

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    void onClick(Controller controller);
}
