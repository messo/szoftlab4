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
    /*
     * Komponens neve
     */
    protected String name;

    /**
     * Konstruktor
     * @param name Komponens neve
     * @param inputCount Komponens bemeneteinek sz�ma
     * @param outputCount Komponens kimeneteinek sz�ma
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
     * Be�ll�tunk egy bemenetet
     *
     * @param inputPin Melyik bemenetet �ll�tjuk
     * @param wire Melyik vezet�ket k�tj�k r�
     */
    public void setInput(int inputPin, Wire wire) {
        Logger.logCall(this, "setInput", new LoggableInt(inputPin), wire);
        inputs[inputPin] = wire;
        Logger.logReturn();
    }

    /**
     * Be�ll�tunk egy kimenetet
     * @param outputPin Melyik kimenetet �ll�tjuk
     * @param wire Melyik vezet�ket k�tj�k r�
     */
    public void setOutput(int outputPin, Wire wire) {
        Logger.logCall(this, "setOutput", new LoggableInt(outputPin), wire);
        outputs[outputPin] = wire;
        Logger.logReturn();
    }

    /**
     * Egy kiv�lasztott kimenet �rt�k�nek lek�rdez�se
     * @param idx Kimenet sorsz�ma
     * @return �rt�k
     */
    public Value getValue(int idx) {
        return outputs[idx].getValue();
    }

    /**
     * Komponens kimeneti l�bain l�v� vezet�keken l�v� �rt�kek �jrasz�mol�sa
     * a bemenetek alapj�n.
     *
     */
    public void evaluate() {
        Logger.logCall(this, "evaluate");
        onEvaluation();
        Logger.logReturn();
    }

    /*
     * Lek�rj�k egy adott bemenetre k�t�tt �rt�ket
     *
     * @param inputPin Bemenet, amely �rdekel minket
     * @return Bementen l�v� �rt�k
     */
    protected Value evaluateInput(int inputPin) {
        return inputs[inputPin].getValue();
    }

    /**
     * Visszaadja, hogy a komponens�nk kimeneti �rt�ke v�ltozott-e a ki�rt�kel�s sor�n
     * @return V�ltozott-e
     */
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

    /**
     * Komponens hozz�ad�sa az �ramk�rh�z
     * @param circuit �ramk�r, amihez hozz�adjuk
     */
    public void addTo(Circuit circuit) {
        Logger.logCall(this, "addTo", circuit);
        circuit.add(this);
        Logger.logReturn();
    }
}
