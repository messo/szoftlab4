package logsim.view.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import logsim.view.Drawable;

/**
 *
 */
public abstract class ComponentView implements Drawable {

    /**
     * Sz�less�g-magass�g
     */
    private final Dimension dimension;
    private static final int pinLength = 5;
    private static final int margin = 6;

    /**
     * Konstruktor a poz�ci�k megad�s�val.
     *
     * @param x
     * @param y
     */
    public ComponentView(int w, int h) {
        this.dimension = new Dimension(w + pinLength * 2, h);
    }

    /**
     * Lek�rhetj�k az objektumt�l a m�ret�t.
     *
     * @return
     */
    @Override
    public final Dimension getDimension() {
        return dimension;
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        // kirajzoljuk a bels�t.
        onDraw(g.create(pinLength, 0, dimension.width - pinLength + 1, dimension.height + 1));

        // kirajzoljuk a p�ck�ket.
        for (int i = 1; i <= getInputPinsCount(); i++) {
            Point p = getRelativeInputPinPosition(i);
            g.drawLine(p.x, p.y, pinLength, p.y);
        }

        for (int i = 1; i <= getOutputPinsCount(); i++) {
            Point p = getRelativeOutputPinPosition(i);
            g.drawLine(p.x - pinLength, p.y, p.x, p.y);
        }
    }

    public Point getRelativeInputPinPosition(int pin) {
        int y = -1;
        int inputs = getInputPinsCount();
        if (inputs == 1) {
            y = dimension.height / 2;
        } else if (inputs > 1) {
            y = margin + (dimension.height - 2 * margin) / (inputs - 1) * (pin - 1);
        }

        return new Point(0, y);
    }

    public Point getRelativeOutputPinPosition(int pin) {
        int y = -1;
        int outputs = getOutputPinsCount();
        if (outputs == 1) {
            y = dimension.height / 2;
        } else if (outputs > 1) {
            y = margin + (dimension.height - 2 * margin) / (outputs - 1) * (pin - 1);
        }

        return new Point(dimension.width, y);
    }

    /**
     * Komponens kirajzol�s�nak egyedilogik�ja
     * @param g
     */
    protected abstract void onDraw(Graphics g);

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    protected abstract int getInputPinsCount();

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    protected abstract int getOutputPinsCount();


//   protected abstract void showDetails(DetailedView dv);
}
