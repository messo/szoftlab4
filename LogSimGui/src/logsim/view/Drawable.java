package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * Áramköri panelre rajzolható objektum.
 */
public interface Drawable {

    /**
     * Kirajzolási logika
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

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    void onClick(Controller controller);
}
