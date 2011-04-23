package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.view.component.ComponentView;

/**
 * Kapcsolót kirajzoló osztály
 */
public class GndView extends ComponentView {

    private static int width = 10;
    private static int height = 11;

    public GndView() {
        super(width, height);
    }

    @Override
    public void onClick(Controller controller) {
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("0", 3, 10);
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
