package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Toggle;
import logsim.view.component.ComponentView;

/**
 * Kapcsol�t kirajzol� oszt�ly
 */
public class ToggleView extends ComponentView {

    private static int width = 15;
    private static int height = 15;
    private Toggle toggle;

    /**
     * Konstruktor
     * @param toggle Megjelen�tend� kapcsol�
     */
    public ToggleView(Toggle toggle) {
        super(width, height);
        this.toggle = toggle;
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(toggle);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    public void onDraw(Graphics g) {
        g.drawRect(0, 0, width, height);
        if (toggle.getValues()[0] == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(2, 2, width - 4, height - 4);
            g.setColor(Color.BLACK);
        }
        g.drawOval(2, 2, width - 4, height - 4);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 0;
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
