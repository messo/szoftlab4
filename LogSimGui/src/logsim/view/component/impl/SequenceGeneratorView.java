package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.SequenceGenerator;
import logsim.view.component.ComponentView;

/**
 * Jelgener�tort kirajzol� oszt�ly
 */
public class SequenceGeneratorView extends ComponentView {

    private static int width = 26;
    private static int height = 12;
    private SequenceGenerator sg;

    /**
     * Konstruktor
     * @param sg Megjelen�tend� jelgener�tor
     */
    public SequenceGeneratorView(SequenceGenerator sg) {
        super(width, height);
        this.sg = sg;
    }

    /**
     * Komponensre kapcsol�s
     * @param controller Megjelen�t� vez�rl�je
     */
    @Override
    public void onClick(Controller controller) {
        controller.onComponentClick(sg);
    }

    /**
     * Kirajzol�si logika
     * @param g
     */
    @Override
    public void onDraw(Graphics g) {
        //g.drawRect(0, 0, width, height);
        g.drawString("seq", 0, 9);
        if (sg.getOutputWire(1).getValue() == Value.TRUE) {
            g.setColor(Color.RED);
            g.fillOval(18, 2, 8, 8);
            g.setColor(Color.BLACK);
        }
        g.drawOval(18, 2, 8, 8);
    }

    /**
     * Bemeneti pinek sz�ma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 0;
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
