package logsim.view;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import logsim.Controller;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;

/**
 * Fõablak interfésze
 */
public interface FrameView {

    /**
     * Itt adható meg, hogy mi történjen, ha sikeres egy szimulációs lépés
     */
    void onSuccessfulSimulation();

    /**
     * Itt adható meg, hogy mi történjen, ha nem stabil az áramkör
     */
    void onFailedSimulation();

    /**
     * Itt kell megadni, hogy a fõablak, hogy tehetõ láthatóvá.
     */
    void makeItVisible();

    /**
     * Szimuláció sebességének beállítása
     * @param pt
     */
    void setPeriod(int pt);

    /**
     * Lekérdezzük a vezérlõt
     * @return
     */
    Controller getController();

    /**
     * Kirajzoljuk az áramkört.
     */
    void drawCircuit();

    /**
     * Beállítjuk a kirajzolandó objektumokat és azok pozícióját.
     *
     * @param drawables kirajzolandó objektumok
     * @param positions pozíciók
     */
    void setDrawables(List<Drawable> drawables, Map<Drawable, Point> positions);

//    /**
//     * Általános komponens részleteit megjeleníti
//     * @param ac Megjelenítendõ alkatrész
//     */
//    void showDetailsAC(AbstractComponent ac);
//
//    /**
//     * Scope komponens részleteinek megjelenítése
//     * @param sc Megjelenítendõ alkatrész
//     */
//    void showDetailsSC(Scope sc);
//
//    /**
//     * Szekvenciagenerátor részleteinek megjelenítése
//     * @param sg Megjelenítendõ szekvenciagenerátor
//     */
//    void showDetailsSG(SequenceGenerator sg);

    public void showDetails(Scope s);
    public void showDetails(SequenceGenerator sg);
    public void showDetails(AbstractComponent ac);
}
