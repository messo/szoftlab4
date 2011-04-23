package logsim.view.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import logsim.Controller;
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
    private Point[] referencePoints;

    public WireView(Wire w, Point start, Point end) {
        this.w = w;
        this.start = start;
        this.end = end;
    }

    /**
     * Vezeték referenciapontjainak a beállítása
     * @param referencePoints
     */
    public void setReferencePoints(Point[] referencePoints) {
        this.referencePoints = referencePoints;
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(start.x, start.y, end.x, end.y);
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
