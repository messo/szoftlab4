package logsim.view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
    private final BufferedImage image;

    /**
     * Konstruktor a poz�ci�k megad�s�val.
     * 
     * @param x
     * @param y
     */
    public Drawable(int w, int h) {
        this.dimension = new Dimension(w, h);

        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    public final Dimension getDimension() {
        return dimension;
    }

    public abstract void onClick(Controller controller);

    /**
     * Komponens rajzunk.
     * @return
     */
    public Image getImage() {
        onDrawing(image.createGraphics());
        return image;
    }

    protected abstract void onDrawing(Graphics2D g);
}
