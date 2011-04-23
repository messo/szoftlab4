package logsim.view.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import logsim.Controller;
import logsim.model.component.Wire;
import logsim.view.Drawable;

/**
 * Egy vezet�k megjelen�t�s��rt felel�s, amit t�r�tt vonallal jelen�t�nk meg.
 */
public class WireView implements Drawable {

    /**
     * Vezet�k, aminek a megjelen�t�s��rt felel.
     */
    private final Wire w;
    /**
     * Vezet�k kezdete
     */
    private Point start;
    /**
     * Vezet�k v�ge
     */
    private Point end;
    /**
     * Vezet�k referenciapontjai, ahol a vezet�k "t�rik".
     */
    private List<Point> referencePoints;

    public WireView(Wire w, Point start, Point end) {
        this.w = w;
        this.start = start;
        this.end = end;
    }

    /**
     * Vezet�k referenciapontjainak a be�ll�t�sa
     * @param referencePoints
     */
    public void setReferencePoints(List<Point> referencePoints) {
        this.referencePoints = referencePoints;
    }

    @Override
    public void draw(Graphics g) {
        Point p1 = start;
        if (referencePoints != null) {
            for (Point p2 : referencePoints) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                p1 = p2;
            }
        }
        g.drawLine(p1.x, p1.y, end.x, end.y);
    }

    @Override
    public Dimension getDimension() {
        // vezet�k nem egy doboz, nincsen "m�rete".
        return null;
    }

    @Override
    public void onClick(Controller controller) {
        // vezet�kre kapcsol�s nem �rdekel minket.
    }
}
