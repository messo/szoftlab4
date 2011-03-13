package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.LoggableInt;
import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Value;

/**
 * Egy komponens absztrakt megval�s�t�sa, ebb�l sz�rmazik az �sszes t�bbi
 * komponens. A k�z�s logik�t val�s�tja meg. A gyakran haszn�lt dolgokra
 * ad alap�rtelmezett implement�ci�t (�sszek�t�s, bemenetek ki�rt�kel�se stb.)
 *
 * @author balint
 */
public abstract class AbstractComponent implements Loggable {

    /**
     * Bemenetekre k�t�tt vezet�kek
     */
    protected Wire[] inputs;
    /*
     * Kimenetekre k�t�tt vezet�kek
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
     * Be�ll�tunk egy bemenetet
     *
     * @param inputPin melyik bemenetet �ll�tjuk
     * @param wire melyik vezet�ket k�tj�k r�
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
        Logger.logCall(this, "evaluate");
        onEvaluation();
        Logger.logReturn();
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
        Logger.logCall(this, "isChanged");
        boolean b = Logger.logAskBool(this, "v�ltozott");
        Logger.logReturn(String.valueOf(b));

        return b;
    }

    /**
     * Ebben a met�dusban kell implement�lni az alkatr�sz logik�j�t, vagyis
     * az adott bemenet(ek) f�ggv�ny�ben mit kell kiadnia a kimenet(ek)en.
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
