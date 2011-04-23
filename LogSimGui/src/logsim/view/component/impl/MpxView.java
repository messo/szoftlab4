package logsim.view.component.impl;

import java.awt.Graphics;
import logsim.Controller;
import logsim.model.component.impl.Mpx;
import logsim.view.component.ComponentView;

/**
 * Multiplexert kirajzol� oszt�ly
 * 
 */
public class MpxView extends ComponentView {

    private Mpx mpx;

    /**
     * Konstruktor
     * @param mpx Megjelen�tend� multiplexer
     */
    public MpxView(Mpx mpx) {
        super(40, 62);
        this.mpx = mpx;
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(mpx);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 40, 62);
        g.drawString("MPX", 10, 35);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 6;
    }

    /**
     * Kimeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 1;
    }
}
