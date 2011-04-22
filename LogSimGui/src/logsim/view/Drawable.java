package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import logsim.Controller;

/**
 * �ramk�ri panelre rajzolhat� objektum.
 */
public abstract class Drawable {

    /**
     * Sz�less�g-magass�g
     */
    private final Dimension dimension;

    /**
     * Konstruktor a poz�ci�k megad�s�val.
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
