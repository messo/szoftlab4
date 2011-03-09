package hu.override.logsim;

import hu.override.logsim.component.AbstractComponent;
import hu.override.logsim.component.FlipFlop;
import hu.override.logsim.component.DisplayComponent;
import hu.override.logsim.component.SourceComponent;
import hu.override.logsim.component.impl.Node;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.parser.SourceReader;
import hu.override.logsim.parser.SourceWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Feladata a jelgenerátor léptetõ kérésére a jelgenerátorok léptetése, a feldolgozó
 * által létrehozott komponensek felvétele az áramkörbe, illetve ezek utasítása arra,
 * hogy töröljék a "már kiértékelve" flaget egy adott kiértékelési ciklus elõtt, hogy ezáltal a
 * ciklusban minden kimenet értéke frissülhessen.
 * Továbbá feladata a kiértékelés elindítása az összes kijelzõre, mert a rendszer kiértékelése
 * a kijelzõk kiértékelésével kezdõdik.
 *
 * @author balint
 */
public class Circuit {

    /**
     * Komponenseket tartalmazó HashMap
     */
    private Map<String, AbstractComponent> componentMap;
    /**
     * Jelforrás típusú komponensek listája (kapcsoló, jelgenerátor)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelenítõ típusú komponensek listája (kijelzõ, 7-szegmenses kijelzõ)
     */
    private List<DisplayComponent> displays;
    /**
     * Flipflopok listája (D és JK flipflopok)
     */
    private List<FlipFlop> flipFlops;
    /**
     * Jelgenerátorok listája
     */


    private List<SequenceGenerator> seqGens;

    public Circuit() {
        componentMap = new HashMap<String, AbstractComponent>();
        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        flipFlops = new ArrayList<FlipFlop>();
        seqGens = new ArrayList<SequenceGenerator>();

    }

    /**
     * Lekérünk egy komponenst az áramkörtõl a neve alapján.
     * 
     * @param name komponens neve
     * @return komponens
     */
    public AbstractComponent getComponentByName(String name) {
        return componentMap.get(name);
    }

    public void add(AbstractComponent c) {
        componentMap.put(c.getName(), c);
    }

    public void add(SourceComponent sc) {
        sources.add(sc);
    }

    public void add(FlipFlop ff) {
        flipFlops.add(ff);
    }

    public void add(SequenceGenerator sg) {
        seqGens.add(sg);
    }

    public void add(DisplayComponent dc) {
        displays.add(dc);
    }


    /**
     * Egy kiértékelési ciklus lefuttatása. Az áramkörtõl ezután lekérdezhetõ, hogy
     * stabil (nem változott semelyik komponens kimenete az utolsó futtatás óta)
     * vagy instabil állapotban van-e.
     */
    public void doEvaluationCycle() {
        // számold ki magad flagek törlése, mivel új ciklus indul
        // ezért mindenkinek ki kell magát számolni újból.
        for (AbstractComponent c : componentMap.values()) {
            c.clearEvaluatedFlag();
        }

        // a megjelenítõkre hívjuk meg az evaluate();
        for (AbstractComponent c : componentMap.values()) {
            // miközben minden kiértékelõdik, lehet, hogy valamelyik
            // komponens instabillá teszi az áramkört, mert változott
            // az õ értéke.
            if (!(c instanceof DisplayComponent))  {
                c.evaluate();
            }
        }
    }

    /**
     * Jelgenerátorok léptetése
     */
    public void stepGenerators() {
        for (SequenceGenerator sg : seqGens) {
            sg.step();
        }
    }

    /**
     * A flipflopok jelenlegi kimenetét elmentjük belsõ állapotnak, és az órajel
     * bemenetén lévõ értéket pedig eltároljuk az éldetektálás érdekében.
     */
    public void commitFlipFlops() {
        for (FlipFlop ff : flipFlops) {
            ff.commit();
        }
    }

    /**
     * Jelforrás típusú komponenseket adja vissza.
     */
    public List<SourceComponent> getSources() {
        return sources;
    }

    /**
     * Megjelenítõ típusú komponeseket adja vissza.
     *
     * @return
     */
    public List<DisplayComponent> getDisplays() {
        return displays;
    }

    /**
     * Fájlból betölti a jelforrások állapotát.
     *
     * @param fileName
     * @return visszajelzés
     */
    public int loadSources(String fileName) {
        SourceReader sr = new SourceReader(fileName);
        sr.loadValuesToSources(sources);

        return 0;
    }

    /**
     * Elmenti fájlba a jelforrások állapotát.
     *
     * @return visszajelzés
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
        for (AbstractComponent c : componentMap.values()) {
            if (c.isChanged()) {
                return true;
            }
        }
        return false;
    }
}
