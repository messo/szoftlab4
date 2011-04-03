package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.LoggableInt;
import logsim.log.Logger;
import logsim.model.Value;

/**
 * Egy komponens absztrakt megval�s�t�sa, ebb�l sz�rmazik az �sszes t�bbi
 * komponens. A k�z�s logik�t val�s�tja meg. A gyakran haszn�lt dolgokra
 * ad alap�rtelmezett implement�ci�t (�sszek�t�s, bemenetek ki�rt�kel�se stb.)
 */
public abstract class AbstractComponent implements Loggable {

    /**
     * Bemenetekre k�t�tt vezet�kek
     */
    protected Wire[] inputs;
    /**
     * Kimenetekre k�t�tt vezet�kek
     */
    protected Wire[] outputs;
    /**
     * Komponens neve
     */
    protected String name;
    /**
     * V�ltozott-e a komponens kimenete
     */
    private boolean changed;

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
     * Be�ll�tunk egy bemenetet
     *
     * @param inputPin Melyik bemenetet �ll�tjuk
     * @param wire Melyik vezet�ket k�tj�k r�
     */
    public void setInput(int inputPin, Wire wire) {
        Logger.logCall(this, "setInput", new LoggableInt(inputPin), wire);
        inputs[inputPin - 1] = wire;
        Logger.logReturn();
    }

    protected Wire getInputWire(int inputPin) {
        return inputs[inputPin - 1];
    }

    protected int getInputsCount() {
        return inputs.length;
    }

    /**
     * Be�ll�tunk egy kimenetet
     * @param outputPin Melyik kimenetet �ll�tjuk
     * @param wire Melyik vezet�ket k�tj�k r�
     */
    public void setOutput(int outputPin, Wire wire) {
        Logger.logCall(this, "setOutput", new LoggableInt(outputPin), wire);
        outputs[outputPin - 1] = wire;
        Logger.logReturn();
    }

    protected Wire getOutputWire(int outputPin) {
        return outputs[outputPin - 1];
    }

    protected int getOutputsCount() {
        return outputs.length;
    }

    /**
     * Komponens kimeneti l�bain l�v� vezet�keken l�v� �rt�kek �jrasz�mol�sa
     * a bemenetek alapj�n.
     *
     */
    public void evaluate() {
        Logger.logCall(this, "evaluate");

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

        Logger.logReturn();
    }

    /**
     * Lek�rj�k egy adott bemenetre k�t�tt �rt�ket
     *
     * @param inputPin Bemenet, amely �rdekel minket
     * @return Bementen l�v� �rt�k
     */
    protected Value getInput(int inputPin) {
        return inputs[inputPin - 1].getValue();
    }

    /**
     * Visszaadja, hogy a komponens�nk kimeneti �rt�ke v�ltozott-e a ki�rt�kel�s sor�n
     * @return V�ltozott-e
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * Ebben a met�dusban kell implement�lni az alkatr�sz logik�j�t, vagyis
     * az adott bemenet(ek) f�ggv�ny�ben mit kell kiadnia a kimenet(ek)re.
     */
    protected abstract void onEvaluation();

    /**
     * Lem�soljuk a komponenst. Ezt a kompozitokn�l haszn�ljuk!
     *
     * @return m�solat
     */
    public abstract AbstractComponent copy(String newName);

    /**
     * Komponens hozz�ad�sa az �ramk�rh�z
     * @param composite �ramk�r, amihez hozz�adjuk
     */
    public void addTo(Composite composite) {
        Logger.logCall(this, "addTo", composite);
        composite.add(this);
        Logger.logReturn();
    }

    public Wire[] getInputsWire()
    {
        return inputs;
    }

    public Wire[] getOutputsWire()
    {
        return outputs;
    }

    @Override
    public String getName() {
        return name;
    }
}
