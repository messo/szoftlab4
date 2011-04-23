package logsim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import logsim.Controller;

/**
 * �ramk�ri panelre rajzolhat� objektum.
 */
public abstract class Drawable {

    /**
     * Sz�less�g-magass�g
     */
    private final Dimension dimension;
    private static final int pinLength = 5;
    private static final int margin = 4;

    /**
     * Konstruktor a poz�ci�k megad�s�val.
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
        // kirajzoljuk a bels�t.
        onDraw(g.create(pinLength, 0, dimension.width - pinLength + 1, dimension.height + 1));
        // kirajzoljuk a p�ck�ket.
        int inputs = getInputPinsCount();
        int outputs = getOutputPinsCount();

        if (inputs == 1) {
            g.drawLine(0, dimension.height / 2 + 1, pinLength, dimension.height / 2 + 1);
        }
        if (outputs == 1) {
            g.drawLine(dimension.width - pinLength, dimension.height / 2 + 1, dimension.width, dimension.height / 2 + 1);
        }
    }

    protected abstract int getInputPinsCount();

    protected abstract int getOutputPinsCount();

    protected abstract void onDraw(Graphics g);
}
