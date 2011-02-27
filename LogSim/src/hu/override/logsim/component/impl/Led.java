package hu.override.logsim.component.impl;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;

/**
 * Egy LED-et reprezent�l, mely vil�g�t, ha
 *
 * @author balint
 */
public class Led extends AbstractComponent implements IsDisplay {

    private Color color;

    /**
     * LED-sz�nt reprezent�l� enum
     */
    public static enum Color {

        RED, YELLOW, BLUE
    }

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, lastValue[0]);
    }

    @Override
    protected void onEvaluation() {
        currentValue[0] = evaluateInput(0);
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }

    /**
     * Be�ll�tjuk a LED sz�n�t
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Lek�rdezz�k a LED sz�n�t
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }
}
