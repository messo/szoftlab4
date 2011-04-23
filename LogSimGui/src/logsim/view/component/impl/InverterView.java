package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Inverter;
import logsim.view.component.ComponentView;

/**
 *
 * @author messo
 */
public class InverterView extends ComponentView {

    private Inverter inv;

    public InverterView(Inverter inv) {
        super(40, 14);
        this.inv = inv;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(inv);
    }

    @Override
    public void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 14);
        g.drawString("INV", 12, 12);
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
