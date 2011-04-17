package logsim.model.component;

import logsim.Viewable;
import logsim.model.Value;

/**
 * Egy komponens absztrakt megvalósítása, ebbõl származik az összes többi
 * komponens. A közös logikát valósítja meg. A gyakran használt dolgokra
 * ad alapértelmezett implementációt (kimenetekre és bemenetekre kötés, kiértékelés stb.)
 */
public abstract class AbstractComponent {

    /**
     * Bemenetekre kötött vezetékek
     */
    protected Wire[] inputs;
    /**
     * Kimenetekre kötött vezetékek
     */
    protected Wire[] outputs;
    /**
     * Komponens neve
     */
    protected String name;
    /**
     * Változott-e a komponens kimenete
     */
    private boolean changed;

    /**
     * Konstruktor
     * @param name Komponens neve
     * @param inputCount Komponens bemeneteinek száma
     * @param outputCount Komponens kimeneteinek száma
     */
    protected AbstractComponent(String name, int inputCount, int outputCount) {
        this.name = name;

        outputs = new Wire[outputCount];
        inputs = new Wire[inputCount];
    }

    /**
     * Beállítunk egy bemenetet
     *
     * @param inputPin Melyik bemenetet állítjuk
     * @param wire Melyik vezetéket kötjük rá
     */
    public void setInput(int inputPin, Wire wire) {
        inputs[inputPin - 1] = wire;
    }

    /**
     * Lekérünk egy bemeneti lábon lévõ vezetéket
     * @param inputPin
     * @return
     */
    public Wire getInputWire(int inputPin) {
        return inputs[inputPin - 1];
    }

    /**
     * Bemeneti lábak száma
     * @return
     */
    public int getInputsCount() {
        return inputs.length;
    }

    /**
     * Beállítunk egy kimenetet
     * @param outputPin Melyik kimenetet állítjuk
     * @param wire Melyik vezetéket kötjük rá
     */
    public void setOutput(int outputPin, Wire wire) {
        outputs[outputPin - 1] = wire;
    }

    /**
     * Lekérünk egy kimeneti lábon lévõ vezetéket
     * @param inputPin Melyik bemenetet állítjuk
     * @return
     */
    public Wire getOutputWire(int outputPin) {
        return outputs[outputPin - 1];
    }

    /**
     * Kimeneti lábak száma
     * @return
     */
    public int getOutputsCount() {
        return outputs.length;
    }

    /**
     * Komponens kimeneti lábain lévõ vezetékeken lévõ értékek újraszámolása
     * a bemenetek alapján.
     *
     */
    public void evaluate() {
        changed = false;
        Value[] oldValues = new Value[outputs.length];
        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] != null) {
                oldValues[i] = outputs[i].getValue();
            }
        }

        onEvaluation();

        for (int i = 0; i < outputs.length; i++) {
            if (outputs[i] != null && oldValues[i] != outputs[i].getValue()) {
                changed = true;
                break;
            }
        }
    }

    /**
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin Bemenet, amely érdekel minket
     * @return Bementen lévõ érték
     */
    protected Value getInput(int inputPin) {
        return inputs[inputPin - 1].getValue();
    }

    /**
     * Visszaadja, hogy a komponensünk kimeneti értéke változott-e a kiértékelés során
     * @return Változott-e
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * Ebben a metódusban kell implementálni az alkatrész logikáját, vagyis
     * az adott bemenet(ek) függvényében mit kell kiadnia a kimenet(ek)re.
     */
    protected abstract void onEvaluation();

    /**
     * Lemásoljuk a komponenst.
     *
     * @return másolat
     */
    public abstract AbstractComponent copy(String newName);

    /**
     * Komponens hozzáadása az áramkörhöz
     * @param composite Áramkör, amihez hozzáadjuk
     */
    public void addTo(Composite composite) {
        composite.add(this);
    }

    /**
     * Komponens nevének lekérése.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Komponens kiírása a viewra.
     * @param view Megjelenítõ
     */
    public void writeTo(Viewable view) {
        view.writeDetails(this);
    }

    /**
     * Kiírja az értékét a viewra (csak kijelzõ és forrásra!)
     * 
     * @param view Megjelenítõ
     */
    public void writeValueTo(Viewable view) {
        throw new IllegalArgumentException("Csak megjelenítõ és forrásra!");
    }
}
