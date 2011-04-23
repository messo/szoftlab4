package logsim.view.component;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.AndGate;
import logsim.view.Drawable;

/**
 *
 * @author messo
 */
public class AndGateView extends Drawable {

    private AndGate ag;

    public AndGateView(AndGate ag) {
        super(100, 100);
        this.ag = ag;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(ag);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("AND", 5, 15);
    }
}
