package hu.override.logsim.component;

import hu.override.logsim.Circuit;
import hu.override.logsim.Value;

/**
 * Egy komponens absztrakt megval�s�t�sa, ebb�l sz�rmazik az �sszes t�bbi
 * komponens. A k�z�s logik�t val�s�tja meg. A gyakran haszn�lt dolgokra
 * ad alap�rtelmezett implement�ci�t (�sszek�t�s, bemenetek ki�rt�kel�se stb.)
 *
 * @author balint
 */
public abstract class AbstractComponent {

    /**
     * Kimenetek t�nyleges �rt�kei, sz�mol�s ut�n ide r�gt�n vissza�rjuk.
     * Ez k�rdezhet� le a felhaszn�l� �ltal.
     */
    protected Value[] values;
    /**
     * Komponens neve (v�ltoz� neve, ahogy a le�r�ban azonos�tjuk)
     */
    protected String name;
    /**
     * Az adott bemenetekre k�t�tt komponensek.
     */
    //protected AbstractComponent[] inputs;

    /**
     * Bemenetekre k�t�tt vezet�kek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre k�t�tt vezet�kek
     */
    protected Wire[] outputs;
    /**
     * Itt t�roljuk, hogy melyik bemenetre, az adott komponens melyik kimenet�t k�t�tt�k.
     */
    //protected int[] indices;
    /**
     * "Ki�rt�kelt" flag, ha ez be van billenve, akkor nem sz�molunk �jra,
     * csak visszaadjuk az el�z�leg kisz�molt �rt�ket.
     */
    protected boolean alreadyEvaluated = false;
    private boolean changed;

    public AbstractComponent() {
        values = new Value[1];
        values[0] = Value.FALSE; // alapb�l innen indulunk.

        outputs = new Wire[1];
    }

    /**
     * N�v be�ll�t�sa (v�ltoz�, amivel azonos�tjuk)
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * N�v lek�rdez�se
     * 
     * @return
     */
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
    //
    //public void setInput(int inputPin, AbstractComponent component, int outputPin) {
    //    System.out.println(String.format("Component: %s, inputSlot: %d. Connected component: %s[%d]",
    //            getName(), inputPin, component.getName(), outputPin));
    //    inputs[inputPin] = component;
    //    indices[inputPin] = outputPin;
    //}
    /*
     * Be�ll�tunk egy bemenetet
     *
     * @param inputPin melyik bemenetet �ll�tjuk
     * @param wire melyik vezet�ket k�tj�k r�
     */
    public void setInput(int inputPin, Wire wire) {
        inputs[inputPin] = wire;
    }

    /**
     * Adott kimeneti l�bon l�v� �rt�ke lek�rdez�se.
     */
    public Value getValue(int idx) {
        return values[idx];
    }

    /**
     * Komponens kimeneteinek ki�rt�kel�se (ha m�g nem volt) �s a kimeneti l�bakon
     * l�v� �rt�kek visszaad�sa.
     *
     * @return kimenetek
     */
    public Value[] evaluate() {
        Value[] tmpValues;

        changed = false;

        // 1. Ki vagy-e sz�molva?
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
     * Bemenetek sz�m�nak be�ll�t�sa
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
//            throw new IllegalArgumentException("Nem j� a bemenetek sz�ma!");
//        }
//    }
    public void setInputPinsCount(int inputPinsCount) {
        inputs = new Wire[inputPinsCount];
        if (!isInputPinsCountValid(inputPinsCount)) {
            throw new IllegalArgumentException("Nem j� a bemenetek sz�ma!");
        }

    }

    /**
     * Lek�rj�k egy adott bemenetre k�t�tt �rt�ket
     *
     * @param inputPin bemenet, amely �rdekel minket.
     * @return
     */
//    protected Value evaluateInput(int inputPin) {
//        return inputs[inputPin].evaluate()[indices[inputPin]];
//    }

    /*
     * Lek�rj�k egy adott bemenetre k�t�tt �rt�ket
     *
     * @param inputPin bemenet, amely �rdekel minket
     * @return bementen l�v� �rt�k
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].getValue();
    }

    /**
     * T�r�lj�k a komponens "ki�rt�kelt" flagj�t.
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
     * Ebben a met�dusban kell implement�lni az alkatr�sz logik�j�t, vagyis
     * az adott bemenet(ek) f�ggv�ny�ben mit kell kiadnia a kimenet(ek)en.
     */
    protected abstract Value[] onEvaluation();

    /**
     * Az alkomponensek itt implement�lhatj�k a bemenetek sz�m�nak ellen�rz�si
     * logik�j�t. Ha ez hamissal t�r vissza, akkor nem �rv�nyes a komponens bementeinek sz�ma.
     *
     * @return �rv�nyes-e a bemenetek sz�ma
     */
    protected boolean isInputPinsCountValid(int inputPinsCount) {
        return true;
    }

    public void addTo(Circuit circuit) {
        circuit.add(this);
    }
}
