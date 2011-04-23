package logsim.view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.Wire;
import logsim.view.Drawable;

/**
 * Egy vezeték megjelenítéséért felelõs, amit törött vonallal jelenítünk meg.
 */
public class WireView implements Drawable {

    /**
     * Vezeték, aminek a megjelenítéséért felel.
     */
    private final Wire w;
    /**
     * Vezeték kezdete
     */
    private Point start;
    /**
     * Vezeték vége
     */
    private Point end;
    /**
     * Vezeték referenciapontjai, ahol a vezeték "törik".
     */
    private List<Point> referencePoints;

    /**
     * Konstruktor
     * @param w Becsomagolt vezeték
     * @param start Kezdõpont
     * @param end Végpont
     */
    public WireView(Wire w, Point start, Point end) {
        this.w = w;
        this.start = start;
        this.end = end;
    }

    /**
     * Vezeték referenciapontjainak a beállítása
     * @param referencePoints
     */
    public void setReferencePoints(List<Point> referencePoints) {
        this.referencePoints = referencePoints;
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        if (w.getValue() == Value.FALSE) {
            g.setColor(Color.LIGHT_GRAY);
        }
        Point p1 = start;
        if (referencePoints != null) {
            for (Point p2 : referencePoints) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                p1 = p2;
            }
        }
        g.drawLine(p1.x, p1.y, end.x, end.y);
        g.setColor(Color.BLACK);
    }

    @Override
    public Dimension getDimension() {
        // vezeték nem egy doboz, nincsen "mérete".
        return null;
    }

    @Override
    public void onClick(Controller controller) {
        // vezetékre kapcsolás nem érdekel minket.
    }
}
