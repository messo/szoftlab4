package logsim.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

/**
 * Áramkört kirajzoló panel.
 */
public class CircuitView extends javax.swing.JPanel implements MouseListener {

    private Map<Drawable, Point> coords;
    private final FrameView parent;
    private List<Drawable> drawables;

    /**
     * Áramkört kirajzoló panel
     * @param drawables
     */
    public CircuitView(FrameView view) {
        this.parent = view;
        this.drawables = null;
        this.coords = null;

        addMouseListener(this);
    }

    public void updateDrawables(List<Drawable> drawables, Map<Drawable, Point> coords) {
        this.drawables = drawables;
        this.coords = coords;
    }

    /**
     * Áramkör kirajzolása
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0F);
        //((Graphics2D) g).setComposite(ac);

        super.paint(g);

        //g.clearRect(0, 0, width, height);
        if (drawables != null) {
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    continue;
                }
                Point point = coords.get(drawable);
                if (point == null) {
                    drawable.draw(g, 0, 0);
                } else {
                    drawable.draw(g, point.x, point.y);
                }
            }
        }

        //g.setColor(Color.RED);
        //g.drawRect(0, 0, 20, 20);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        // meghatározzuk, hogy mire kapcsoltunk.
        Drawable drawable = null;

        for (Drawable d : drawables) {
            if (d.getDimension() != null && coords.get(d) != null) {
                drawable = d;
                break;
            }
        }

        drawable.onClick(parent.getController());
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    /**
     * Áramkör újrarajzolása.
     */
    public void refresh() {
        revalidate();
    }
}
