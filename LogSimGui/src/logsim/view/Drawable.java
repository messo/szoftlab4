package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * Áramköri panelre rajzolható objektum.
 */
public interface Drawable {

    /**
     * Itt írjuk le, hogy pontosan mit kell kirajzolni.
     * 
     * @param g
     */
    void draw(Graphics g);

    /**
     * Lekérhetjük az objektumtól a méretét.
     * 
     * @return
     */
    Dimension getDimension();

    void onClick(Controller controller);
}
