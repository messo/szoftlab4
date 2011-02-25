package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 *
 * @author balint
 */
public abstract class Component {

    protected Circuit circuit;
    protected Value[] lastValue;
    protected Value[] currentValue;
    protected String name;
    protected Component[] inputs;
    protected int[] indices;
    protected boolean alreadyEvaluated = false;

    public Component() {
        lastValue = new Value[1];
        currentValue = new Value[1];
        lastValue[0] = Value.FALSE; // alapból innen indulunk.
    }

    public void setCircuit(Circuit parent) {
        this.circuit = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Beállítunk egy bemenetet.
     *
     * @param inputPin melyik bemenetet állítjuk be
     * @param component melyik komponenst kötjük rá az adott komponesre
     * @param outputPin a rákötött komponens, melyik kimenetét használjuk.
     */
    public void setInput(int inputPin, Component component, int outputPin) {
        System.out.println(String.format("Component: %s, inputSlot: %d. Connected component: %s[%d]",
                getName(), inputPin, component.getName(), outputPin));
        inputs[inputPin] = component;
        indices[inputPin] = outputPin;
    }

    public void setInput(int inputSlot, Component component) {
        setInput(inputSlot, component, 0);
    }

    public Value getValue() {
        return getValue(0);
    }

    public Value getValue(int idx) {
        return lastValue[idx];
    }

    /**
     * Számolás:
     */
    public Value evaluate(int outputPin) {
        // 1. Ki vagy-e számolva?
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
        inputs = new Component[inputPinsCount];
        indices = new int[inputPinsCount];
        for (int i = 0; i < inputPinsCount; i++) {
            indices[i] = 0;
        }
    }

    protected Value getInputValue(int inputPin) {
        return inputs[inputPin].getValue(indices[inputPin]);
    }

    protected abstract void onEvaluation();

    public void clearEvaluatedFlag() {
        alreadyEvaluated = false;
    }
}
