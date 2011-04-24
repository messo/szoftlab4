package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Scope;
import logsim.view.component.ComponentView;

/**
 * Scope-ot kirajzoló osztály.
 */
public class ScopeView extends ComponentView {

    private static int width = 12;
    private static int height = 12;
    private Scope scope;

    /**
     * Konstruktor
     * @param led Megjelenítendõ oszcilloszkóp
     */
    public ScopeView(Scope scope) {
        super(width, height);
        this.scope = scope;
    }

    /**
     * Komponensre kacsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(scope);
    }

    /**
     * Kirajzolási logika
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
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    /**
     * Kimeneti pinek száma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 0;
    }
}
