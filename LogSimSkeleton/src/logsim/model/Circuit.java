package logsim.model;

import logsim.model.component.AbstractComponent;
import logsim.model.component.DisplayComponent;
import logsim.model.component.SourceComponent;
import java.util.ArrayList;
import java.util.List;
import logsim.log.Loggable;
import logsim.log.Logger;

/**
 * �ramk�rt reprezent�l� oszt�ly.
 * Feladata a komponensek felv�tele az �ramk�rbe, illetve ezek ki�rt�kel�se.
 */
public class Circuit implements Loggable {

    /**
     * Komponensek list�ja
     */
    private List<AbstractComponent> components;
    /**
     * Jelforr�s t�pus� komponensek list�ja (pl. kapcsol�)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelen�t� t�pus� komponensek list�ja (pl. led)
     */
    private List<DisplayComponent> displays;

    public Circuit() {
        Logger.logCreate(this);
        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        components = new ArrayList<AbstractComponent>();
        Logger.logReturn();
    }

    /**
     * �ramk�r inicializ�l�sa
     */
    public void init() {
        // Tesztesetekben implement�ljuk.
    }

    /**
     * �ltal�nos t�pus� komponens hozz�ad�sa az �ramk�rh�z
     * @param c Hozz�adand� komponens
     */
    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("�ltal�nos komponens hozz�ad�sa az �ramk�rh�z");
        components.add(c);
        Logger.logReturn();
    }

    /**
     * Jelforr�s t�pus� komponens hozz�ad�sa az �ramk�rh�z
     * @param sc Hozz�adand� komponens
     */
    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforr�s hozz�ad�sa az �ramk�rh�z");
        components.add(sc);
        sources.add(sc);
        Logger.logReturn();
    }

    /**
     * Kijelz� t�pus� komponens hozz�ad�sa az �ramk�rh�z
     * @param dc Hozz�adand� komponens
     */
    public void add(DisplayComponent dc) {
        Logger.logCall(this, "add", dc);
        Logger.logComment("Kijelz� hozz�ad�sa az �ramk�rh�z");
        components.add(dc);
        displays.add(dc);
        Logger.logReturn();
    }

    /**
     * Egy ki�rt�kel�si ciklus lefuttat�sa. Az �ramk�rt�l ezut�n lek�rdezhet�, hogy
     * stabil (nem v�ltozott semelyik komponens kimenete az utols� futtat�s �ta)
     * vagy instabil �llapotban van-e.
     */
    public void doEvaluationCycle() {
        Logger.logCall(this, "doEvaluationCycle");

        // minden komponenst ki�rt�kel�nk
        for (AbstractComponent c : components) {
            c.evaluate();
        }

        Logger.logReturn();
    }

    /**
     * Jelgener�torok l�ptet�se
     */
    public void stepGenerators() {
        Logger.logCall(this, "stepGenerators");
        // TODO
        Logger.logReturn();
    }

    /**
     * A flipflopok jelenlegi kimenet�t elmentj�k bels� �llapotnak, �s az �rajel
     * bemenet�n l�v� �rt�ket pedig elt�roljuk az �ldetekt�l�s �rdek�ben.
     */
    public void commitFlipFlops() {
        Logger.logCall(this, "commitFlipFlops");
        // TODO
        Logger.logReturn();
    }

    /**
     * Jelforr�s t�pus� komponenseket adja vissza.
     *
     * @return Jellforr�sokat tartalmaz� list�t ad vissza
     */
    public List<SourceComponent> getSources() {
        return sources;
    }

    /**
     * Megjelen�t� t�pus� komponeseket adja vissza.
     *
     * @return Megjelen�t�ket tartalmaz� lista
     */
    public List<DisplayComponent> getDisplays() {
        return displays;
    }

    /**
     * Megvizsg�lja, hogy az �ramk�rben t�rt�nt-e v�ltoz�s
     *
     * @return v�ltozott-e
     */
    public boolean isChanged() {
        boolean ret;
        Logger.logCall(this, "isChanged");
        ret = false;
        for (AbstractComponent c : components) {
            if (c.isChanged()) {
                ret = true;
                break;
            }
        }

        Logger.logReturn(String.valueOf(ret));
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "circuit";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Circuit";
    }
}
