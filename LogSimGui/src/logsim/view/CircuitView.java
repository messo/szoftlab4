package logsim.view;

import java.awt.Color;
import java.awt.Dimension;
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
    private FrameView parent;
    private List<Drawable> drawables;

    /**
     * Áramkört kirajzoló panel
     * @param drawables
     */
    public CircuitView() {
        this.drawables = null;
        this.coords = null;

        setBackground(Color.white);

        addMouseListener(this);
    }

    public void setParent(FrameView parent) {
        this.parent = parent;
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
                    drawable.draw(g);
                } else {
                    drawable.draw(g.create(point.x, point.y, drawable.getDimension().width+1, drawable.getDimension().height+1));
                }
            }
        }

        //g.setColor(Color.RED);
        //g.drawRect(0, 0, 20, 20);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Drawable drawable = null;

        int x = me.getX();
        int y = me.getY();

        Dimension dim;
        Point p;
        for (Drawable d : drawables) {
            dim = d.getDimension();
            p = coords.get(d);
            if (dim != null && p != null
                    && p.x <= x && p.y <= y
                    && x <= p.x + dim.width && y <= p.y + dim.height) {
                drawable = d;
                break;
            }
        }

        if (drawable != null) {
            drawable.onClick(parent.getController());
        }
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
        repaint();
    }
}
