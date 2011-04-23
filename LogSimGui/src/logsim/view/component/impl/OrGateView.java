package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.OrGate;
import logsim.view.component.ComponentView;

/**
 *
 * @author messo
 */
public class OrGateView extends ComponentView {

    private OrGate og;

    public OrGateView(OrGate og) {
        super(40, 30);
        this.og = og;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(og);
    }

    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 30);
        g.drawString("OR", 13, 19);
    }

    @Override
    protected int getInputPinsCount() {
        return og.getInputsCount();
    }

    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
