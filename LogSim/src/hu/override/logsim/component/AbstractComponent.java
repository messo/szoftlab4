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
     * Komponens neve (v�ltoz� neve, ahogy a le�r�ban azonos�tjuk)
     */
    protected String name;
    /**
     * Az adott bemenetekre k�t�tt komponensek.
     */
    /**
     * Bemenetekre k�t�tt vezet�kek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre k�t�tt vezet�kek
     */
    protected Wire[] outputs;
    private boolean changed;

    public AbstractComponent() {
        inputs = new Wire[1];
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

    /*
     * Be�ll�tunk egy bemenetet
     *
     * @param inputPin melyik bemenetet �ll�tjuk
     * @param wire melyik vezet�ket k�tj�k r�
     */
    public void setInput(int inputPin, Wire wire) {
        inputs[inputPin] = wire;
    }

    public void setOutput(int outputPin, Wire wire) {
        outputs[outputPin] = wire;
    }

    /**
     * Adott kimeneti l�bon l�v� �rt�k lek�rdez�se.
     */
    public Value getValue(int idx) {
        return outputs[idx].getValue();
    }

    /**
     * Komponens kimeneti l�bain l�v� vezet�keken l�v� �rt�kek �jrasz�mol�sa
     * a bemenetek alapj�n.
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
     * Lek�rj�k egy adott bemenetre k�t�tt �rt�ket
     *
     * @param inputPin bemenet, amely �rdekel minket
     * @return bementen l�v� �rt�k
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
     * Ebben a met�dusban kell implement�lni az alkatr�sz logik�j�t, vagyis
     * az adott bemenet(ek) f�ggv�ny�ben mit kell kiadnia a kimenet(ek)en.
     */
    protected abstract void onEvaluation();

    public void addTo(Circuit circuit) {
        circuit.add(this);
    }
}
