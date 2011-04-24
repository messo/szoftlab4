package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Scope;
import logsim.view.component.ComponentView;

/**
 * Scope-ot kirajzol� oszt�ly.
 */
public class ScopeView extends ComponentView {

    private static int width = 12;
    private static int height = 12;
    private Scope scope;

    /**
     * Konstruktor
     * @param led Megjelen�tend� oszcilloszk�p
     */
    public ScopeView(Scope scope) {
        super(width, height);
        this.scope = scope;
    }

    /**
     * Komponensre kacsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(scope);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        if (scope.getValue() == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(0, 0, width, height);
            g.setColor(Color.BLACK);
        }
        g.drawOval(0, 0, width, height);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 0;
    }
}
