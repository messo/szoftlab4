package logsim.view;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import logsim.Controller;

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
}
