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
 * Feladata a jelgenerátor léptetõ kérésére a jelgenerátorok léptetése, a feldolgozó
 * által létrehozott komponensek felvétele az áramkörbe, illetve ezek utasítása arra,
 * hogy töröljék a "már kiértékelve" flaget egy adott kiértékelési ciklus elõtt, hogy ezáltal a
 * ciklusban minden kimenet értéke frissülhessen.
 * Továbbá feladata a kiértékelés elindítása az összes kijelzõre, mert a rendszer kiértékelése
 * a kijelzõk kiértékelésével kezdõdik.
 *
 * @author balint
 */
public class Circuit implements Loggable {

    /**
     * Komponensek listája
     */
    private List<AbstractComponent> components;
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
        Logger.logCreate(this);
        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        flipFlops = new ArrayList<FlipFlop>();
        seqGens = new ArrayList<SequenceGenerator>();
        components = new ArrayList<AbstractComponent>();
        Logger.logReturn();
    }

    public void init() {
        // Tesztesetekben implementáljuk.
    }

    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("Általános komponens hozzáadása az áramkörhöz");
        components.add(c);
        Logger.logReturn();
    }

    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforrás hozzáadása az áramkörhöz");
        components.add(sc);
        sources.add(sc);
        Logger.logReturn();
    }

    public void add(FlipFlop ff) {
        Logger.logCall(this, "add", ff);
        Logger.logComment("FF hozzáadása az áramkörhöz");
        components.add(ff);
        flipFlops.add(ff);
        Logger.logReturn();
    }

    public void add(SequenceGenerator sg) {
        Logger.logCall(this, "add", sg);
        Logger.logComment("Jelgenerátor hozzáadása az áramkörhöz");
        components.add(sg);
        seqGens.add(sg);
        Logger.logReturn();
    }

    public void add(DisplayComponent dc) {
        Logger.logCall(this, "add", dc);
        Logger.logComment("Kijelzõ hozzáadása az áramkörhöz");
        components.add(dc);
        displays.add(dc);
        Logger.logReturn();
    }

    /**
     * Egy kiértékelési ciklus lefuttatása. Az áramkörtõl ezután lekérdezhetõ, hogy
     * stabil (nem változott semelyik komponens kimenete az utolsó futtatás óta)
     * vagy instabil állapotban van-e.
     */
    public void doEvaluationCycle() {
        Logger.logCall(this, "doEvaluationCycle");

        for (AbstractComponent c : components) {
            // miközben minden kiértékelõdik, lehet, hogy valamelyik
            // komponens instabillá teszi az áramkört, mert változott
            // valamelyik kimenete
            c.evaluate();
        }

        Logger.logReturn();
    }

    /**
     * Jelgenerátorok léptetése
     */
    public void stepGenerators() {
        Logger.logCall(this, "stepGenerators");
        for (SequenceGenerator sg : seqGens) {
            sg.step();
        }
        Logger.logReturn();
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
