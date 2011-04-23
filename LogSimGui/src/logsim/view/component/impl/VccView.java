package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * Kapcsol�t kirajzol� oszt�ly
 */
public class VccView extends ComponentView {

    private static int width = 10;
    private static int height = 11;

    public VccView() {
        super(width, height);
    }

    @Override
    public void onClick(Controller controller) {
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.drawString("1", 3, 10);
        g.setColor(Color.BLACK);
    }

    @Override
    protected int getInputPinsCount() {
        return 0;
    }

    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
