package logsim.view.component;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.Toggle;
import logsim.view.Drawable;

/**
 * Kapcsolót kirajzoló osztály
 */
public class ToggleView extends Drawable {

    private static int width = 15;
    private static int height = 15;
    private Toggle toggle;

    public ToggleView(Toggle toggle) {
        super(width, height);
        this.toggle = toggle;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(toggle);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(0, 0, width, height);
        if (toggle.getValues()[0] == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(2, 2, width - 4, height - 4);
            g.setColor(Color.BLACK);
        }
        g.drawOval(2, 2, width - 4, height - 4);
    }
}
