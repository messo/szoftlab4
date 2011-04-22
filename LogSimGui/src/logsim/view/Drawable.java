package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import logsim.Controller;

/**
 * Áramköri panelre rajzolható objektum.
 */
public abstract class Drawable {

    /**
     * Szélesség-magasság
     */
    private final Dimension dimension;

    /**
     * Konstruktor a pozíciók megadásával.
     * 
     * @param x
     * @param y
     */
    public Drawable(int w, int h) {
        this.dimension = new Dimension(w, h);
    }

    public final Dimension getDimension() {
        return dimension;
    }

    public abstract void onClick(Controller controller);

    public abstract void draw(Graphics g, int x, int y);
}
