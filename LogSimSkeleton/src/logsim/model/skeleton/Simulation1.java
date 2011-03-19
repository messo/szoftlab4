package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 * 1. tesztesethez tartozó szimuláció
 */
public class Simulation1 extends Simulation {

    public Simulation1() {
        Logger.logCreate(this);
        Circuit c = new Circuit1();
        // inicializáljuk az áramkört
        c.init();
        // beregisztráljuk a szimulációnál
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsoló és egy led van összekötve.
     */
    private static class Circuit1 extends Circuit {

        @Override
        public void init() {
            // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
            // majd létrehozuk egy "wire" nevû vezeték elemet
            Logger.logCall(this, "init");
            Wire wire = new Wire("wire");

            // Létrehozunk egy "toggle" nevû kapcsoló elemet
            // majd a kimenetére rákötjük az elõzõleg létrehozott vezetéket
            Toggle toggle = new Toggle("toggle");
            toggle.setOutput(0, wire);

            // Létrehozunk egy "led" nevû kijelzõ(led) elemet
            // majd a bemenetére a fent létrehozott vezetéket kötjük
            Led led = new Led("led");
            led.setInput(0, wire);

            // hozzáadjuk az áramkörhöz a létrehozott kapcsolót és ledet
            toggle.addTo(this);
            led.addTo(this);

            // utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
            Logger.logReturn();
        }
    }
}
