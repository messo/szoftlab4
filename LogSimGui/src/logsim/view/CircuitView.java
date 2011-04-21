package logsim.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

/**
 * Áramkört kirajzoló panel.
 */
public class CircuitView extends javax.swing.JPanel implements MouseListener {

    private final Map<Drawable, Point> coords;
    private FrameView parent;
    private List<Drawable> drawables;
    private Graphics g;
    private Image image;
    private int width;
    private int height;

    public CircuitView() {
        coords = null;
    }

    /**
     * Áramkört kirajzoló panel
     * @param drawables
     */
    public CircuitView(FrameView view, List<Drawable> drawables, Map<Drawable, Point> coords) {
        this.coords = coords;
        this.parent = view;
        this.drawables = drawables;
        // számolni!
        width = 100;
        height = 100;

        //this.image = new BufferedImage(
//                width, height,
//                BufferedImage.TYPE_INT_ARGB);
//        this.g = image.createGraphics();
        this.image = createImage(width, height);
        //AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0F);
        //g.setComposite(ac);

        addMouseListener(this);
    }

    /**
     * Áramkör kirajzolása
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, 0, 0, null);
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
        //g.clearRect(0, 0, width, height);
        if (drawables != null) {
            for (Drawable drawable : drawables) {
                Point point = coords.get(drawable);
                //g.drawImage(drawable.getImage(), point.x, point.y, null);
            }
        }

        g.setColor(Color.RED);
        g.drawRect(0, 0, 20, 20);

        invalidate();
    }
}
