package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * Áramköri panelre rajzolható objektum.
 */
public abstract class Drawable {

    /**
     * Szélesség-magasság
     */
    private final Dimension dimension;
    private static final int pinLength = 5;
    private static final int margin = 6;

    /**
     * Konstruktor a pozíciók megadásával.
     * 
     * @param x
     * @param y
     */
    public Drawable(int w, int h) {
        this.dimension = new Dimension(w + pinLength * 2, h);
    }

    public final Dimension getDimension() {
        return dimension;
    }

    public abstract void onClick(Controller controller);

    public void draw(Graphics g) {
        // kirajzoljuk a belsõt.
        onDraw(g.create(pinLength, 0, dimension.width - pinLength + 1, dimension.height + 1));
        // kirajzoljuk a pöcköket.
        int inputs = getInputPinsCount();
        int outputs = getOutputPinsCount();

        if (inputs == 1) {
            g.drawLine(0, dimension.height / 2, pinLength, dimension.height / 2);
        } else if (inputs > 1) {
            // osszuk szét egyenletesen
            int sep = (dimension.height - 2 * margin) / (inputs - 1);
            int y = margin;
            for (int i = 0; i < inputs; i++) {
                g.drawLine(0, y, pinLength, y);
                y += sep;
            }
        }

        if (outputs == 1) {
            g.drawLine(dimension.width - pinLength, dimension.height / 2, dimension.width, dimension.height / 2);
        } else if (outputs > 1) {
            // osszuk szét egyenletesen
            int sep = (dimension.height - 2 * margin) / (outputs - 1);
            int y = margin;
            for (int i = 0; i < inputs; i++) {
                g.drawLine(dimension.width - pinLength, dimension.width, pinLength, y);
                y += sep;
            }
        }
    }

    protected abstract int getInputPinsCount();

    protected abstract int getOutputPinsCount();

    protected abstract void onDraw(Graphics g);
}
