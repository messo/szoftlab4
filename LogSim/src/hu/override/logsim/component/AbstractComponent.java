package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 * Egy komponens absztrakt megvalósítása, ebbõl származik az összes többi
 * komponens. A közös logikát valósítja meg. A gyakran használt dolgokra
 * ad alapértelmezett implementációt (összekötés, bemenetek kiértékelése stb.)
 *
 * @author balint
 */
public abstract class AbstractComponent {

    /**
     * Komponens neve (változó neve, ahogy a leíróban azonosítjuk)
     */
    protected String name;
    /**
     * Az adott bemenetekre kötött komponensek.
     */
    /**
     * Bemenetekre kötött vezetékek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre kötött vezetékek
     */
    protected Wire[] outputs;
    private boolean changed;

    public AbstractComponent() {
        inputs = new Wire[1];
        outputs = new Wire[1];
    }

    /**
     * Név beállítása (változó, amivel azonosítjuk)
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Név lekérdezése
     * 
     * @return
     */
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
        inputs[inputPin] = wire;
    }

    public void setOutput(int outputPin, Wire wire) {
        outputs[outputPin] = wire;
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
        changed = false;

        Value[] tmp = new Value[outputs.length];
        for (int i = 0; i < outputs.length; i++) {
            tmp[i] = outputs[i].getValue();
        }

        onEvaluation();
        for (int i = 0; i < outputs.length; i++) {
            if (tmp[i] != outputs[i].getValue()) {
                changed = true;
            }
        }
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
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * Ebben a metódusban kell implementálni az alkatrész logikáját, vagyis
     * az adott bemenet(ek) függvényében mit kell kiadnia a kimenet(ek)en.
     */
    protected abstract void onEvaluation();

    public void addTo(Circuit circuit) {
        circuit.add(this);
    }
}
