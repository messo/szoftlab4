package logsim.model.component;

import logsim.ComponentViewCreator;
import logsim.GuiController;
import logsim.model.Value;
import logsim.view.Drawable;

/**
 * Vezet�k oszt�ly. K�t komponens-l�bat k�t �ssze. A rajta l�v� �rt�k lek�rdezhet�
 * �s be�ll�that�.
 */
public class Wire {

    /**
     * Vezet�ken l�v� �rt�k
     */
    private Value value = Value.FALSE;

    /**
     * Vezet�k �rt�k�nek be�ll�t�sa
     * @param value �rt�k
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * Vezet�k �rt�k�nek lek�r�se
     * @return Vezet�k �rt�ke
     */
    public Value getValue() {
        return value;
    }

    /**
     * Lek�rj�k a vezet�ket �br�zol� viewt, de a t�nyleges rajzol�st nem mi v�gezz�k, hanem
     * a ComponentViewCreator, kihaszn�lva a Visitor tervez�si mint�t.
     *
     * @param cvc
     * @return rajzolhat� vezet�k az �ramk�ri panelre
     */
    public Drawable createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
