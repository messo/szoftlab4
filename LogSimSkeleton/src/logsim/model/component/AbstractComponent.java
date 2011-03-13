package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.LoggableInt;
import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Egy komponens absztrakt megvalósítása, ebbõl származik az összes többi
 * komponens. A közös logikát valósítja meg. A gyakran használt dolgokra
 * ad alapértelmezett implementációt (összekötés, bemenetek kiértékelése stb.)
 *
 * @author balint
 */
public abstract class AbstractComponent implements Loggable {

    /**
     * Bemenetekre kötött vezetékek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre kötött vezetékek
     */
    protected Wire[] outputs;
    protected String name;

    public AbstractComponent(String name) {
        this.name = name;
        Logger.logCreate(this);
        inputs = new Wire[1];
        outputs = new Wire[1];
    }

    @Override
    public String getName() {
        return name;
    }

    /*
     * Beállítunk egy bemenetet
     *
     * @param inputPin melyik bemenetet állítjuk
     * @param wire melyik vezetéket kötjük rá
     */
    public void setInput(int inputPin, Wire wire) {
        Logger.logCall(this, "setInput", new LoggableInt(inputPin), wire);
        inputs[inputPin] = wire;
        Logger.logReturn();
    }

    public void setOutput(int outputPin, Wire wire) {
        Logger.logCall(this, "setOutput", new LoggableInt(outputPin), wire);
        outputs[outputPin] = wire;
        Logger.logReturn();
    }

    /**
     * Adott kimeneti lábon lévõ érték lekérdezése.
     */
    public Value getValue(int idx) {
        return outputs[idx].getValue();
    }

    /**
     * Komponens kimeneti lábain lévõ vezetékeken lévõ értékek újraszámolása
     * a bemenetek alapján.
     *
     * @return kimenetek
     */
    public void evaluate() {
        Logger.logCall(this, "evaluate");
        onEvaluation();
        Logger.logReturn();
    }

    /*
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin bemenet, amely érdekel minket
     * @return bementen lévõ érték
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].getValue();
    }

    public boolean isChanged() {
        Logger.logCall(this, "isChanged");
        boolean b = Logger.logAskBool(this, "változott");
        Logger.logReturn(String.valueOf(b));

        return b;
    }

    /**
     * Ebben a metódusban kell implementálni az alkatrész logikáját, vagyis
     * az adott bemenet(ek) függvényében mit kell kiadnia a kimenet(ek)en.
     */
    protected abstract void onEvaluation();

    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }

    @Override
    public String getClassName() {
        return "ABSTRACTCOMPONENT!!!!";
    }
}
