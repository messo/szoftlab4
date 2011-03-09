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
     * Kimenetek tényleges értékei, számolás után ide rögtön visszaírjuk.
     * Ez kérdezhetõ le a felhasználó által.
     */
    protected Value[] values;
    /**
     * Komponens neve (változó neve, ahogy a leíróban azonosítjuk)
     */
    protected String name;
    /**
     * Az adott bemenetekre kötött komponensek.
     */
    //protected AbstractComponent[] inputs;

    /**
     * Bemenetekre kötött vezetékek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre kötött vezetékek
     */
    protected Wire[] outputs;
    /**
     * Itt tároljuk, hogy melyik bemenetre, az adott komponens melyik kimenetét kötöttük.
     */
    //protected int[] indices;
    /**
     * "Kiértékelt" flag, ha ez be van billenve, akkor nem számolunk újra,
     * csak visszaadjuk az elõzõleg kiszámolt értéket.
     */
    protected boolean alreadyEvaluated = false;
    private boolean changed;

    public AbstractComponent() {
        values = new Value[1];
        values[0] = Value.FALSE; // alapból innen indulunk.

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

    /**
     * Beállítunk egy bemenetet.
     *
     * @param inputPin melyik bemenetet állítjuk be
     * @param component melyik komponenst kötjük rá az adott komponesre
     * @param outputPin a rákötött komponens, melyik kimenetét használjuk.
     */
    //
    //public void setInput(int inputPin, AbstractComponent component, int outputPin) {
    //    System.out.println(String.format("Component: %s, inputSlot: %d. Connected component: %s[%d]",
    //            getName(), inputPin, component.getName(), outputPin));
    //    inputs[inputPin] = component;
    //    indices[inputPin] = outputPin;
    //}
    /*
     * Beállítunk egy bemenetet
     *
     * @param inputPin melyik bemenetet állítjuk
     * @param wire melyik vezetéket kötjük rá
     */
    public void setInput(int inputPin, Wire wire) {
        inputs[inputPin] = wire;
    }

    /**
     * Adott kimeneti lábon lévõ értéke lekérdezése.
     */
    public Value getValue(int idx) {
        return values[idx];
    }

    /**
     * Komponens kimeneteinek kiértékelése (ha még nem volt) és a kimeneti lábakon
     * lévõ értékek visszaadása.
     *
     * @return kimenetek
     */
    public Value[] evaluate() {
        Value[] tmpValues;

        changed = false;

        // 1. Ki vagy-e számolva?
        if (!alreadyEvaluated) {
            alreadyEvaluated = true;
            tmpValues = onEvaluation();
            for (int i = 0; i < values.length; i++) {
                if (values[i] != tmpValues[i]) {
                    changed = true;
                }
                values[i] = tmpValues[i];
            }
        }

        return values;
    }

    /**
     * Bemenetek számának beállítása
     * 
     * @param inputPinsCount
     */
//    public void setInputPinsCount(int inputPinsCount) {
//        inputs = new AbstractComponent[inputPinsCount];
//        if (isInputPinsCountValid(inputPinsCount)) {
//            indices = new int[inputPinsCount];
//            for (int i = 0; i < inputPinsCount; i++) {
//                indices[i] = 0;
//            }
//        } else {
//            throw new IllegalArgumentException("Nem jó a bemenetek száma!");
//        }
//    }
    public void setInputPinsCount(int inputPinsCount) {
        inputs = new Wire[inputPinsCount];
        if (!isInputPinsCountValid(inputPinsCount)) {
            throw new IllegalArgumentException("Nem jó a bemenetek száma!");
        }

    }

    /**
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin bemenet, amely érdekel minket.
     * @return
     */
//    protected Value evaluateInput(int inputPin) {
//        return inputs[inputPin].evaluate()[indices[inputPin]];
//    }

    /*
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin bemenet, amely érdekel minket
     * @return bementen lévõ érték
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].getValue();
    }

    /**
     * Töröljük a komponens "kiértékelt" flagjét.
     */
    public void clearEvaluatedFlag() {
        alreadyEvaluated = false;
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
    protected abstract Value[] onEvaluation();

    /**
     * Az alkomponensek itt implementálhatják a bemenetek számának ellenõrzési
     * logikáját. Ha ez hamissal tér vissza, akkor nem érvényes a komponens bementeinek száma.
     *
     * @return érvényes-e a bemenetek száma
     */
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return true;
    }

    public void addTo(Circuit circuit) {
        circuit.add(this);
    }
}
