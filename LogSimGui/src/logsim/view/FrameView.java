package logsim.view;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import logsim.Controller;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;

/**
 * F�ablak interf�sze
 */
public interface FrameView {

    /**
     * Itt adhat� meg, hogy mi t�rt�njen, ha sikeres egy szimul�ci�s l�p�s
     */
    void onSuccessfulSimulation();

    /**
     * Itt adhat� meg, hogy mi t�rt�njen, ha nem stabil az �ramk�r
     */
    void onFailedSimulation();

    /**
     * Itt kell megadni, hogy a f�ablak, hogy tehet� l�that�v�.
     */
    void makeItVisible();

    /**
     * Szimul�ci� sebess�g�nek be�ll�t�sa
     * @param pt
     */
    void setPeriod(int pt);

    /**
     * Lek�rdezz�k a vez�rl�t
     * @return
     */
    Controller getController();

    /**
     * Kirajzoljuk az �ramk�rt.
     */
    void drawCircuit();

    /**
     * Be�ll�tjuk a kirajzoland� objektumokat �s azok poz�ci�j�t.
     *
     * @param drawables kirajzoland� objektumok
     * @param positions poz�ci�k
     */
    void setDrawables(List<Drawable> drawables, Map<Drawable, Point> positions);

//    /**
//     * �ltal�nos komponens r�szleteit megjelen�ti
//     * @param ac Megjelen�tend� alkatr�sz
//     */
//    void showDetailsAC(AbstractComponent ac);
//
//    /**
//     * Scope komponens r�szleteinek megjelen�t�se
//     * @param sc Megjelen�tend� alkatr�sz
//     */
//    void showDetailsSC(Scope sc);
//
//    /**
//     * Szekvenciagener�tor r�szleteinek megjelen�t�se
//     * @param sg Megjelen�tend� szekvenciagener�tor
//     */
//    void showDetailsSG(SequenceGenerator sg);

    public void showDetails(Scope s);
    public void showDetails(SequenceGenerator sg);
    public void showDetails(AbstractComponent ac);
}
