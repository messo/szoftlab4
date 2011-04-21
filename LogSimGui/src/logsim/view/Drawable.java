package logsim.view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
    private final BufferedImage image;

    /**
     * Konstruktor a pozíciók megadásával.
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
