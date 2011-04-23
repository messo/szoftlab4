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
        super(40, 29);
        this.inv = inv;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    @Override
    public void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 29);
        g.drawString("INV", 12, 19);
    }

    @Override
    protected int getInputPinsCount() {
        return 1;
    }

    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
