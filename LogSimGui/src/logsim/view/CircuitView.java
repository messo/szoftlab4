package logsim.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

/**
 * Áramkört kirajzoló panel.
 */
public class CircuitView extends javax.swing.JPanel implements MouseListener {

    private Map<Drawable, Point> positions;
    private FrameView parent;
    private List<Drawable> drawables;

    /**
     * Áramkört kirajzoló panel
     * 
     */
    public CircuitView() {
        this.drawables = null;
        this.positions = null;

        setBackground(Color.white);

        addMouseListener(this);
    }

    /**
     * Szülõ beállítása
     * @param parent Szülõ
     */
    public void setParent(FrameView parent) {
        this.parent = parent;
    }

    /**
     * Kirajzolandó objektumok és koordinátáik beállítása
     * @param drawables Megjelenítendõ objektumok
     * @param coords Koordináták
     */
    public void updateDrawables(List<Drawable> drawables, Map<Drawable, Point> coords) {
        this.drawables = drawables;
        this.positions = coords;
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

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        if (drawables != null) {
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    continue;
                }
                Point position = positions.get(drawable);
                if (position != null && drawable.getDimension() != null) {
                    // ha van pozíciója és dimenziója, akkor csak oda engedjük
                    // õt rajzolni.
                    drawable.draw(g.create(position.x, position.y,
                            drawable.getDimension().width + 1,
                            drawable.getDimension().height + 1));
                } else {
                    // nem tudunk róla semmit, valószínûleg vezeték
                    drawable.draw(g);
                }
            }
        }
    }

    /**
     * Egérkattintás kezelõ
     * @param me
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();

        Point p = null;
        Dimension dim = null;

        for (Drawable d : drawables) {
            p = positions.get(d);
            dim = d.getDimension();
            if (dim != null && p != null
                    && p.x <= x && p.y <= y
                    && x <= p.x + dim.width && y <= p.y + dim.height) {
                d.onClick(parent.getController());
                break;
            }
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
