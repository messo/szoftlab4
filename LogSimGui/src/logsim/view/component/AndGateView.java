/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim.view.component;

import java.awt.Graphics2D;
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
    protected void onDrawing(Graphics2D g) {
        
    }
}
