package logsim.view;

import java.awt.Graphics;
import java.awt.Point;
import logsim.Controller;
import logsim.model.component.Wire;

/**
 * Egy vezeték megjelenítéséért felelõs, amit törött vonallal jelenítünk meg.
 */
public class WireView extends Drawable {

    /**
     * Vezeték, aminek a megjelenítéséért felel.
     */
    private final Wire w;
    /**
     * Vezeték referenciapontjai, ahol a vezeték "törik".
     */
    private Point[] referencePoints;

    public WireView(Wire w) {
        super(100, 100);
        this.w = w;
    }

    @Override
    public void onClick(Controller controller) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void draw(Graphics g) {
    }

    /**
     * Vezeték referenciapontjainak a beállítása
     * @param referencePoints
     */
    public void setReferencePoints(Point[] referencePoints) {
        this.referencePoints = referencePoints;
    }
}
