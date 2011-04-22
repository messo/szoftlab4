package logsim.view;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import logsim.Controller;

/**
 *
 */
public interface FrameView {

    void onSuccessfulSimulation();

    void onFailedSimulation();

    void makeItVisible();

    Controller getController();

    void drawCircuit(List<Drawable> drawables, Map<Drawable, Point> coords);
}
