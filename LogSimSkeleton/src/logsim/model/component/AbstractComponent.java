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
    /*
     * Komponens neve
     */
    protected String name;

    /**
     * Konstruktor
     * @param name Komponens neve
     * @param inputCount Komponens bemeneteinek száma
     * @param outputCount Komponens kimeneteinek száma
     */
    protected AbstractComponent(String name, int inputCount, int outputCount) {
        this.name = name;
        Logger.logCreate(this);

        outputs = new Wire[outputCount];
        inputs = new Wire[inputCount];

        Logger.logReturn();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Beállítunk egy bemenetet
     *
     * @param inputPin Melyik bemenetet állítjuk
     * @param wire Melyik vezetéket kötjük rá
     */
    public void setInput(int inputPin, Wire wire) {
        Logger.logCall(this, "setInput", new LoggableInt(inputPin), wire);
        inputs[inputPin] = wire;
        Logger.logReturn();
    }

    /**
     * Beállítunk egy kimenetet
     * @param outputPin Melyik kimenetet állítjuk
     * @param wire Melyik vezetéket kötjük rá
     */
    public void setOutput(int outputPin, Wire wire) {
        Logger.logCall(this, "setOutput", new LoggableInt(outputPin), wire);
        outputs[outputPin] = wire;
        Logger.logReturn();
    }

    /**
     * Egy kiválasztott kimenet értékének lekérdezése
     * @param idx Kimenet sorszáma
     * @return Érték
     */
    public Value getValue(int idx) {
        return outputs[idx].getValue();
    }

    /**
     * Komponens kimeneti lábain lévõ vezetékeken lévõ értékek újraszámolása
     * a bemenetek alapján.
     *
     */
    public void evaluate() {
        Logger.logCall(this, "evaluate");
        onEvaluation();
        Logger.logReturn();
    }

    /*
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin Bemenet, amely érdekel minket
     * @return Bementen lévõ érték
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].getValue();
    }

    /**
     * Visszaadja, hogy a komponensünk kimeneti értéke változott-e a kiértékelés során
     * @return Változott-e
     */
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

    /**
     * Komponens hozzáadása az áramkörhöz
     * @param circuit Áramkör, amihez hozzáadjuk
     */
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
