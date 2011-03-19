package logsim.model;

import logsim.model.component.AbstractComponent;
import logsim.model.component.DisplayComponent;
import logsim.model.component.SourceComponent;
import java.util.ArrayList;
import java.util.List;
import logsim.log.Loggable;
import logsim.log.Logger;

/**
 * Áramkört reprezentáló osztály.
 * Feladata a komponensek felvétele az áramkörbe, illetve ezek kiértékelése.
 */
public class Circuit implements Loggable {

    /**
     * Komponensek listája
     */
    private List<AbstractComponent> components;
    /**
     * Jelforrás típusú komponensek listája (pl. kapcsoló)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelenítõ típusú komponensek listája (pl. led)
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
     * Áramkör inicializálása
     */
    public void init() {
        // Tesztesetekben implementáljuk.
    }

    /**
     * Általános típusú komponens hozzáadása az áramkörhöz
     * @param c Hozzáadandó komponens
     */
    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("Általános komponens hozzáadása az áramkörhöz");
        components.add(c);
        Logger.logReturn();
    }

    /**
     * Jelforrás típusú komponens hozzáadása az áramkörhöz
     * @param sc Hozzáadandó komponens
     */
    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforrás hozzáadása az áramkörhöz");
        components.add(sc);
        sources.add(sc);
        Logger.logReturn();
    }

    /**
     * Kijelzõ típusú komponens hozzáadása az áramkörhöz
     * @param dc Hozzáadandó komponens
     */
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

        // minden komponenst kiértékelünk
        for (AbstractComponent c : components) {
            c.evaluate();
        }

        Logger.logReturn();
    }

    /**
     * Jelgenerátorok léptetése
     */
    public void stepGenerators() {
        Logger.logCall(this, "stepGenerators");
        // TODO
        Logger.logReturn();
    }

    /**
     * A flipflopok jelenlegi kimenetét elmentjük belsõ állapotnak, és az órajel
     * bemenetén lévõ értéket pedig eltároljuk az éldetektálás érdekében.
     */
    public void commitFlipFlops() {
        Logger.logCall(this, "commitFlipFlops");
        // TODO
        Logger.logReturn();
    }

    /**
     * Jelforrás típusú komponenseket adja vissza.
     *
     * @return Jellforrásokat tartalmazó listát ad vissza
     */
    public List<SourceComponent> getSources() {
        return sources;
    }

    /**
     * Megjelenítõ típusú komponeseket adja vissza.
     *
     * @return Megjelenítõket tartalmazó lista
     */
    public List<DisplayComponent> getDisplays() {
        return displays;
    }

    /**
     * Megvizsgálja, hogy az áramkörben történt-e változás
     *
     * @return változott-e
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
