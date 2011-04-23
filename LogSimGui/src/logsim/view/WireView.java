package logsim.view;

import java.awt.Graphics;
import java.awt.Point;
import logsim.Controller;
import logsim.model.component.Wire;

/**
 * Egy vezet�k megjelen�t�s��rt felel�s, amit t�r�tt vonallal jelen�t�nk meg.
 */
public class WireView extends Drawable {

    /**
     * Vezet�k, aminek a megjelen�t�s��rt felel.
     */
    private final Wire w;
    /**
     * Vezet�k referenciapontjai, ahol a vezet�k "t�rik".
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
     * Vezet�k referenciapontjainak a be�ll�t�sa
     * @param referencePoints
     */
    public void setReferencePoints(Point[] referencePoints) {
        this.referencePoints = referencePoints;
    }
}
