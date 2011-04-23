package logsim.model;

import java.util.HashMap;
import java.util.Map;
import logsim.model.component.AbstractComponent;
import logsim.model.component.Composite;
import logsim.model.component.Pin;
import logsim.model.component.Wire;

/**
 * Áramkört reprezentáló osztály, igazából egy kompozit. Felelõssége megegyzik a kompozitéval.
 */
public class Circuit extends Composite {

    private Map<Wire, Pin> inputPins = new HashMap<Wire, Pin>();
    private Map<Wire, Pin> outputPins = new HashMap<Wire, Pin>();

    public Circuit() {
        super("Circuit", "circuit", 0, 0);
    }

    @Override
    protected void onWireCreated(Wire wire, AbstractComponent source, int outputPin, AbstractComponent target, int inputPin) {
        inputPins.put(wire, new Pin(target, inputPin, Pin.Type.INPUT));
        outputPins.put(wire, new Pin(source, outputPin, Pin.Type.OUTPUT));
    }

    public Pin getInputPinForWire(Wire wire) {
        return inputPins.get(wire);
    }

    public Pin getOutputPinForWire(Wire wire) {
        return outputPins.get(wire);
    }
}
