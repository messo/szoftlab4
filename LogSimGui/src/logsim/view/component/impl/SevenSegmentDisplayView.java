package logsim.view.component.impl;

import java.awt.Color;
import java.awt.Graphics;
import logsim.Controller;
import logsim.model.Value;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.view.component.ComponentView;

/**
 * Hétszegmenses kijelzõt kirajzoló osztály.
 */
public class SevenSegmentDisplayView extends ComponentView {

    private SevenSegmentDisplay ssd;

    /**
     * Konstriktor
     * @param ssd Megjelenítendõ kijelzõ
     */
    public SevenSegmentDisplayView(SevenSegmentDisplay ssd) {
        super(41, 73);
        this.ssd = ssd;
    }

    /**
     * Komponensre kapcsolás
     * @param controller Megjelenítõ vezérlõje
     */
    @Override
    public void onClick(Controller controller) {
    }

    /**
     * Kirajzolási logika
     * @param g
     */
    @Override
    protected void onDraw(Graphics g) {
        g.drawRect(0, 0, 41, 73);
        //1. szegmens
        if (ssd.getInputWire(1).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{7, 35, 30, 12}, new int[]{5, 5, 10, 10}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{7, 35, 30, 12}, new int[]{5, 5, 10, 10}, 4);
        }
        //2. szegmens
        if (ssd.getInputWire(2).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{37, 37, 32, 32}, new int[]{5, 37, 32, 10}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{37, 37, 32, 32}, new int[]{5, 37, 32, 10}, 4);
        }
        //3. szegmens
        if (ssd.getInputWire(3).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{37, 37, 32, 32}, new int[]{37, 69, 64, 42}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{37, 37, 32, 32}, new int[]{37, 69, 64, 42}, 4);
        }
        //4. szegmens
        if (ssd.getInputWire(4).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{12, 30, 35, 7}, new int[]{64, 64, 69, 69}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{12, 30, 35, 7}, new int[]{64, 64, 69, 69}, 4);
        }
        //5. szegmens
        if (ssd.getInputWire(4).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{10, 10, 5, 5}, new int[]{42, 64, 69, 37}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{10, 10, 5, 5}, new int[]{42, 64, 69, 37}, 4);
        }
        //6. szegmens
        if (ssd.getInputWire(6).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{10, 10, 5, 5}, new int[]{10, 32, 37, 5}, 4);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{10, 10, 5, 5}, new int[]{10, 32, 37, 5}, 4);
        }
        //7. szegmens
        if (ssd.getInputWire(6).getValue() == Value.TRUE) {
            g.setColor(Color.red);
            g.fillPolygon(new int[]{7, 10, 32, 35, 32, 10}, new int[]{37, 34, 34, 37, 40, 40}, 6);
        } else {
            g.setColor(Color.white);
            g.drawPolygon(new int[]{7, 10, 32, 35, 32, 10}, new int[]{37, 34, 34, 37, 40, 40}, 6);
        }
    }

    /**
     * Bemeneti pinek száma
     * @return
     */
    @Override
    protected int getInputPinsCount() {
        return 7;
    }

    /**
     * Kimeneti pinek száma
     * @return
     */
    @Override
    protected int getOutputPinsCount() {
        return 0;
    }

}
