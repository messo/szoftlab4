package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 * Egy komponens absztrakt megvalósítása, ebbõl származik az összes többi
 * komponens. A közös logikát valósítja meg. A gyakran használt dolgokra
 * ad alapértelmezett implementációt (összekötés, bemenetek kiértékelése stb.)
 *
 * @author balint
 */
public abstract class AbstractComponent implements Component {

    /**
     * Kimenetek tényleges értékei, számolás után ide rögtön visszaírjuk.
     * Ez kérdezhetõ le a felhasználó által.
     */
    protected Value[] lastValue;
    /**
     * Jelenlegi (számolás közben) érték, ezt csak rövid ideig tároljuk,
     * ahhoz kell, hogy tudjuk változik-e a lastValue-hoz képest.
     */
    protected Value[] currentValue;
    /**
     * Komponens neve (változó neve, ahogy a leíróban azonosítjuk)
     */
    protected String name;
    /**
     * Az adott bemenetekre kötött komponensek.
     */
    protected AbstractComponent[] inputs;
    /**
     * Itt tároljuk, hogy melyik bemenetre, az adott komponens melyik kimenetét kötöttük.
     */
    protected int[] indices;
    /**
     * "Kiértékelt" flag, ha ez be van billenve, akkor nem számolunk újra,
     * csak visszaadjuk az elõzõleg kiszámolt értéket.
     */
    protected boolean alreadyEvaluated = false;
    private boolean changed;

    public AbstractComponent() {
        lastValue = new Value[1];
        currentValue = new Value[1];
        lastValue[0] = Value.FALSE; // alapból innen indulunk.
    }

    /**
     * Név beállítása (változó, amivel azonosítjuk)
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Név lekérdezése
     * 
     * @return
     */
    @Override
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
    public void setInput(int inputPin, AbstractComponent component, int outputPin) {
        System.out.println(String.format("Component: %s, inputSlot: %d. Connected component: %s[%d]",
                getName(), inputPin, component.getName(), outputPin));
        inputs[inputPin] = component;
        indices[inputPin] = outputPin;
    }

    /**
     * Shortcut a másik setInput()-hoz, outputPin = 0-val.
     *
     * @param inputSlot
     * @param component
     */
    public void setInput(int inputSlot, AbstractComponent component) {
        setInput(inputSlot, component, 0);
    }

    /**
     * Adott kimeneti lábon lévõ értéke lekérdezése.
     */
    @Override
    public Value getValue(int idx) {
        return lastValue[idx];
    }

    /**
     * 0-ás kimeneti lábon lévõ értéke lekérdezése.
     */
    @Override
    public Value getValue() {
        return getValue(0);
    }

    /**
     * Komponens kimeneteinek kiértékelése (ha még nem volt) és a megadott
     * kimeneti lábon lévõ érték visszaadása.
     *
     * @param outputPin
     */
    public Value[] evaluate() {
        // 1. Ki vagy-e számolva?
        if (!alreadyEvaluated) {
            alreadyEvaluated = true;
            onEvaluation();
        }

        changed = false;
        for (int i = 0; i < lastValue.length; i++) {
            if (currentValue[i] != null && lastValue[i] != currentValue[i]) {
                lastValue[i] = currentValue[i];
                changed = true;
            }
        }

        return lastValue;
    }

    /**
     * Bemenetek számának beállítása
     * 
     * @param inputPinsCount
     */
    public void setInputPinsCount(int inputPinsCount) {
        inputs = new AbstractComponent[inputPinsCount];
        if (isInputPinsCountValid(inputPinsCount)) {
            indices = new int[inputPinsCount];
            for (int i = 0; i < inputPinsCount; i++) {
                indices[i] = 0;
            }
        } else {
            throw new IllegalArgumentException("Nem jó a bemenetek száma!");
        }
    }

    /**
     * Lekérjük egy adott bemenetre kötött értéket
     *
     * @param inputPin bemenet, amely érdekel minket.
     * @return
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].evaluate()[indices[inputPin]];
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
    protected abstract void onEvaluation();

    /**
     * Az alkomponensek itt implementálhatják a bemenetek számának ellenõrzési
     * logikáját. Ha ez hamissal tér vissza, akkor nem érvényes a komponens bementeinek száma.
     *
     * @return érvényes-e a bemenetek száma
     */
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return true;
    }
}
