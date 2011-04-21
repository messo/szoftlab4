package logsim.view;

import java.awt.Graphics2D;
import logsim.Controller;
import logsim.model.component.Wire;

/**
 *
 */
public class WireView extends Drawable {

    private final Wire w;

    public WireView(Wire w) {
        super(100, 100);
        this.w = w;
    }

    @Override
    public void onClick(Controller controller) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onDrawing(Graphics2D g) {
    }
}
