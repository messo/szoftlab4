package logsim.model.component;

import java.awt.Point;
import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.view.component.WireView;

/**
 * Vezeték osztály. Két komponens-lábat köt össze. A rajta lévõ érték lekérdezhetõ
 * és beállítható.
 */
public class Wire {

    /**
     * Vezetéken lévõ érték
     */
    private Value value = Value.FALSE;

    /**
     * Vezeték értékének beállítása
     * @param value Érték
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Vezeték értékének lekérése
     * @return Vezeték értéke
     */
    public Value getValue() {
        return value;
    }

    /**
     * Lekérjük a vezetéket ábrázoló viewt, de a tényleges rajzolást nem mi végezzük, hanem
     * a ComponentViewCreator, kihasználva a Visitor tervezési mintát.
     *
     * @param cvc
     * @return rajzolható vezeték az áramköri panelre
     */
    public WireView createView(ComponentViewCreator cvc, Point start, Point end) {
        return cvc.createView(this, start, end);
    }
}
