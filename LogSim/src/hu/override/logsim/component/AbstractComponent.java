package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 *
 * @author balint
 */
public abstract class AbstractComponent implements Component {

    protected Circuit circuit;
    protected Value[] lastValue;
    protected Value[] currentValue;
    protected String name;
    protected AbstractComponent[] inputs;
    protected int[] indices;
    protected boolean alreadyEvaluated = false;

    public AbstractComponent() {
        lastValue = new Value[1];
        currentValue = new Value[1];
        lastValue[0] = Value.FALSE; // alapb�l innen indulunk.
    }

    public void setCircuit(Circuit parent) {
        this.circuit = parent;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Be�ll�tunk egy bemenetet.
     *
     * @param inputPin melyik bemenetet �ll�tjuk be
     * @param component melyik komponenst k�tj�k r� az adott komponesre
     * @param outputPin a r�k�t�tt komponens, melyik kimenet�t haszn�ljuk.
     */
    public void setInput(int inputPin, AbstractComponent component, int outputPin) {
        System.out.println(String.format("Component: %s, inputSlot: %d. Connected component: %s[%d]",
                getName(), inputPin, component.getName(), outputPin));
        inputs[inputPin] = component;
        indices[inputPin] = outputPin;
    }

    public void setInput(int inputSlot, AbstractComponent component) {
        setInput(inputSlot, component, 0);
    }

    public Value getValue() {
        return getValue(0);
    }

    public Value getValue(int idx) {
        return lastValue[idx];
    }

    /**
     * Sz�mol�s:
     */
    public Value evaluate(int outputPin) {
        // 1. Ki vagy-e sz�molva?
        if (!alreadyEvaluated) {
            alreadyEvaluated = true;
            onEvaluation();
        }

        for (int i = 0; i < lastValue.length; i++) {
            if (currentValue[i] != null && lastValue[i] != currentValue[i]) {
                circuit.setStable(false);
                lastValue[i] = currentValue[i];
            }
        }

        return lastValue[outputPin];
    }

    public Value evaluate() {
        return evaluate(0);
    }

    public void setInputPinsCount(int inputPinsCount) {
        inputs = new AbstractComponent[inputPinsCount];
        if (isInputPinsCountValid(inputPinsCount)) {
            indices = new int[inputPinsCount];
            for (int i = 0; i < inputPinsCount; i++) {
                indices[i] = 0;
            }
        } else {
            throw new IllegalArgumentException("Nem j� a bemenetek sz�ma!");
        }
    }

    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].evaluate(indices[inputPin]);
    }

    public void clearEvaluatedFlag() {
        alreadyEvaluated = false;
    }

    /**
     * Ebben a met�dusban kell implement�lni az alkatr�sz logik�j�t, vagyis
     * az adott bemenet(ek) f�ggv�ny�ben mit kell kiadnia a kimenet(ek)en.
     */
    protected abstract void onEvaluation();

    /**
     * Az alkomponensek itt implement�lhatj�k a bemenetek sz�m�nak ellen�rz�si
     * logik�j�t.
     *
     * @return
     */
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return true;
    }
}
