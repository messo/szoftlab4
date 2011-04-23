package logsim.view.component;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Led;
import logsim.view.Drawable;

/**
 * LED-et kirajzoló osztály.
 */
public class LedView extends Drawable {

    private static int width = 11;
    private static int height = 11;
    private Led led;

    public LedView(Led led) {
        super(width, height);
        this.led = led;
    }

    @Override
    public void onClick(Controller controller) {
    }

    @Override
    public void draw(Graphics g) {
        if (led.getValue() == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(0, 0, width, height);
            g.setColor(Color.BLACK);
        }
        g.drawOval(0, 0, width, height);
    }
}
