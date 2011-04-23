package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Mpx;
import logsim.view.component.ComponentView;

/**
 *
 * @author messo
 */
public class MpxView extends ComponentView {

    private Mpx mpx;

    public MpxView(Mpx mpx) {
        super(40, 62);
        this.mpx = mpx;
    }

    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(mpx);
    }

    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 62);
        g.drawString("MPX", 10, 35);
    }

    @Override
    protected int getInputPinsCount() {
        return 6;
    }

    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
