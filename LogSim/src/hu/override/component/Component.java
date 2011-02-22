package hu.override.component;

/**
 *
 * @author balint
 */
public abstract class Component {

    protected boolean[] lastValue;
    protected Boolean[] currentValue;
    protected String name;
    protected Component[] inputs;
    protected int[] indices;
    private boolean alreadyEvaluated = false;
    private HasDirtyFlag parent;

    public Component() {
        init();
    }

    public void init() {
        lastValue = new boolean[1];
        currentValue = new Boolean[1];
    }

    public void setParent(HasDirtyFlag parent) {
        this.parent = parent;
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

    public boolean getImmediateValue() {
        return getImmediateValue(0);
    }

    public boolean getImmediateValue(int idx) {
        return lastValue[idx];
    }

    /**
     * Számolás:
     */
    public boolean evaluate(int outputPin) {
        // 1. Ki vagy-e számolva?
        if (!alreadyEvaluated) {
            alreadyEvaluated = true;
            onEvaluation();
        }

        for (int i = 0; i < lastValue.length; i++) {
            if (currentValue[i] != null && lastValue[i] != currentValue[i]) {
                parent.setDirtyFlag();
                lastValue[i] = currentValue[i];
            }
        }

        return lastValue[outputPin];
    }

    public boolean evaluate() {
        return evaluate(0);
    }

    public void setInputPinsCount(int inputPinsCount) {
        inputs = new Component[inputPinsCount];
        indices = new int[inputPinsCount];
        for (int i = 0; i < inputPinsCount; i++) {
            indices[i] = 0;
        }
    }

    protected boolean getInputValue(int inputPin) {
        return inputs[inputPin].getImmediateValue(indices[inputPin]);
    }

    protected abstract void onEvaluation();

    public void clearEvaluatedFlag() {
        alreadyEvaluated = false;
    }
}
