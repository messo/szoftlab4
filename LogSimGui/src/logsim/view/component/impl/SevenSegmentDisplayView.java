package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.view.component.ComponentView;

/**
 * LED-et kirajzoló osztály.
 */
public class SevenSegmentDisplayView extends ComponentView {

    private SevenSegmentDisplay ssd;

    public SevenSegmentDisplayView(SevenSegmentDisplay ssd) {
        super(40, 72);
        this.ssd = ssd;
    }

    @Override
    public void onClick(Controller controller) {
    }

    @Override
    public void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 72);
    }

    @Override
    protected int getInputPinsCount() {
        return 7;
    }

    @Override
    protected int getOutputPinsCount() {
        return 0;
    }
}
