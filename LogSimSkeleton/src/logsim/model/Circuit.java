package logsim.model;

import logsim.model.component.AbstractComponent;
import logsim.model.component.FlipFlop;
import logsim.model.component.DisplayComponent;
import logsim.model.component.SourceComponent;
import logsim.model.component.impl.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import logsim.log.Loggable;
import logsim.log.Logger;

/**
 * Feladata a jelgener�tor l�ptet� k�r�s�re a jelgener�torok l�ptet�se, a feldolgoz�
 * �ltal l�trehozott komponensek felv�tele az �ramk�rbe, illetve ezek utas�t�sa arra,
 * hogy t�r�lj�k a "m�r ki�rt�kelve" flaget egy adott ki�rt�kel�si ciklus el�tt, hogy ez�ltal a
 * ciklusban minden kimenet �rt�ke friss�lhessen.
 * Tov�bb� feladata a ki�rt�kel�s elind�t�sa az �sszes kijelz�re, mert a rendszer ki�rt�kel�se
 * a kijelz�k ki�rt�kel�s�vel kezd�dik.
 *
 * @author balint
 */
public class Circuit implements Loggable {

    /**
     * Komponensek list�ja
     */
    private List<AbstractComponent> components;
    /**
     * Jelforr�s t�pus� komponensek list�ja (kapcsol�, jelgener�tor)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelen�t� t�pus� komponensek list�ja (kijelz�, 7-szegmenses kijelz�)
     */
    private List<DisplayComponent> displays;
    /**
     * Flipflopok list�ja (D �s JK flipflopok)
     */
    private List<FlipFlop> flipFlops;
    /**
     * Jelgener�torok list�ja
     */
    private List<SequenceGenerator> seqGens;

    public Circuit() {
        Logger.logCreate(this);
        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        flipFlops = new ArrayList<FlipFlop>();
        seqGens = new ArrayList<SequenceGenerator>();
        components = new ArrayList<AbstractComponent>();
        Logger.logReturn();
    }

    public void init() {
        // Tesztesetekben implement�ljuk.
    }

    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("�ltal�nos komponens hozz�ad�sa az �ramk�rh�z");
        components.add(c);
        Logger.logReturn();
    }

    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforr�s hozz�ad�sa az �ramk�rh�z");
        components.add(sc);
        sources.add(sc);
        Logger.logReturn();
    }

    public void add(FlipFlop ff) {
        Logger.logCall(this, "add", ff);
        Logger.logComment("FF hozz�ad�sa az �ramk�rh�z");
        components.add(ff);
        flipFlops.add(ff);
        Logger.logReturn();
    }

    public void add(SequenceGenerator sg) {
        Logger.logCall(this, "add", sg);
        Logger.logComment("Jelgener�tor hozz�ad�sa az �ramk�rh�z");
        components.add(sg);
        seqGens.add(sg);
        Logger.logReturn();
    }

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

        for (AbstractComponent c : components) {
            // mik�zben minden ki�rt�kel�dik, lehet, hogy valamelyik
            // komponens instabill� teszi az �ramk�rt, mert v�ltozott
            // valamelyik kimenete
            c.evaluate();
        }

        Logger.logReturn();
    }

    /**
     * Jelgener�torok l�ptet�se
     */
    public void stepGenerators() {
        Logger.logCall(this, "stepGenerators");
        for (SequenceGenerator sg : seqGens) {
            sg.step();
        }
        Logger.logReturn();
    }

    /**
     * A flipflopok jelenlegi kimenet�t elmentj�k bels� �llapotnak, �s az �rajel
     * bemenet�n l�v� �rt�ket pedig elt�roljuk az �ldetekt�l�s �rdek�ben.
     */
    public void commitFlipFlops() {
        for (FlipFlop ff : flipFlops) {
            ff.commit();
        }
    }

    /**
     * Jelforr�s t�pus� komponenseket adja vissza.
     */
    public List<SourceComponent> getSources() {
        return sources;
    }

    /**
     * Megjelen�t� t�pus� komponeseket adja vissza.
     *
     * @return
     */
    public List<DisplayComponent> getDisplays() {
        return displays;
    }

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

    @Override
    public String getName() {
        return "circuit";
    }

    @Override
    public String getClassName() {
        return "Circuit";
    }
}
