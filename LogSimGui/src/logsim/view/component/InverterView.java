package logsim.view.component;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Inverter;
import logsim.view.Drawable;

/**
 *
 * @author messo
 */
public class InverterView extends Drawable {

    private Inverter inv;

    public InverterView(Inverter inv) {
        super(100, 100);
        this.inv = inv;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("INV", 5, 15);
    }
}
