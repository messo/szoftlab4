package hu.override.logsim.component.impl;

import hu.override.logsim.Value;
import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.IsDisplay;

/**
 * Egy LED-et reprezentál, mely világít, ha bemenetén igaz érték van.
 * 3 féle színe lehet, ezeket a Color enumeráció határozza meg.
 *
 * @author balint
 */
public class Led extends AbstractComponent implements IsDisplay {

    /**
     * Led jelenlegi színe
     */
    private Color color;

    /**
     * LED-színt reprezentáló enum
     */
    public static enum Color {

        RED, YELLOW, BLUE
    }

    @Override
    public String toString() {
        return String.format("LED(%s): %s", name, values[0]);
    }

    @Override
    protected Value[] onEvaluation() {
        Value[] result = new Value[values.length];

        result[0] = evaluateInput(0);
        return result;
    }

    @Override
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return inputPinsCount == 1;
    }

    /**
     * Beállítjuk a LED színét
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Lekérdezzük a LED színét
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }
}
