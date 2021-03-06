package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.FlipFlop;
import hu.override.logsim.component.DisplayComponent;
import hu.override.logsim.component.SourceComponent;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.parser.SourceReader;
import hu.override.logsim.parser.SourceWriter;
import java.util.ArrayList;
import java.util.List;

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
public class Circuit {

    /**
     * Komponenseket tartalmaz� HashMap
     */
    //private Map<String, AbstractComponent> componentMap;
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
    private List<AbstractComponent> components;

    public Circuit() {
        System.out.println("CREATE Circuit circuit");
        //componentMap = new HashMap<String, AbstractComponent>();
        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        flipFlops = new ArrayList<FlipFlop>();
        seqGens = new ArrayList<SequenceGenerator>();
        components = new ArrayList<AbstractComponent>();
    }

    /**
     * Lek�r�nk egy komponenst az �ramk�rt�l a neve alapj�n.
     * 
     * @param name komponens neve
     * @return komponens
     */
    public AbstractComponent getComponentByName(String name) {
        //return componentMap.get(name);
        return null;
    }

    public void add(AbstractComponent c) {
        //componentMap.put(c.getName(), c);
        // egyel�re el�g a lista.
        components.add(c);
    }

    public void add(SourceComponent sc) {
        components.add(sc);
        sources.add(sc);
    }

    public void add(FlipFlop ff) {
        components.add(ff);
        flipFlops.add(ff);
    }

    public void add(SequenceGenerator sg) {
        components.add(sg);
        seqGens.add(sg);
    }

    public void add(DisplayComponent dc) {
        components.add(dc);
        displays.add(dc);
    }

    /**
     * Egy ki�rt�kel�si ciklus lefuttat�sa. Az �ramk�rt�l ezut�n lek�rdezhet�, hogy
     * stabil (nem v�ltozott semelyik komponens kimenete az utols� futtat�s �ta)
     * vagy instabil �llapotban van-e.
     */
    public void doEvaluationCycle() {
        System.out.println("  CALL circuit.doEvaluationCycle()");

        // a megjelen�t�kre h�vjuk meg az evaluate();
        for (AbstractComponent c : components) {
            // mik�zben minden ki�rt�kel�dik, lehet, hogy valamelyik
            // komponens instabill� teszi az �ramk�rt, mert v�ltozott
            // az � �rt�ke.
            c.evaluate();
        }

        System.out.println("  RETURN");
    }

    /**
     * Jelgener�torok l�ptet�se
     */
    public void stepGenerators() {
        for (SequenceGenerator sg : seqGens) {
            sg.step();
        }
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

    /**
     * F�jlb�l bet�lti a jelforr�sok �llapot�t.
     *
     * @param fileName
     * @return visszajelz�s
     */
    public int loadSources(String fileName) {
        SourceReader sr = new SourceReader(fileName);
        sr.loadValuesToSources(sources);

        return 0;
    }

    /**
     * Elmenti f�jlba a jelforr�sok �llapot�t.
     *
     * @return visszajelz�s
     */
    public int saveSources(String fileName) {
        SourceWriter sw = new SourceWriter(fileName);
        for (SourceComponent source : sources) {
            sw.add(source);
        }
        sw.close();

        return 0;
    }

    public boolean isChanged() {
        System.out.println("  CALL circuit.isChanged()");
        for (AbstractComponent c : components) {
            if (c.isChanged()) {
                System.out.println("  RETURN true");
                return true;
            }
        }
        System.out.println("  RETURN false");
        return false;
    }
}
