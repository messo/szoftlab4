package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 * 2. tesztesethez tartozó szimuláció
 */
public class Simulation2 extends Simulation {

    public Simulation2() {
        Logger.logCreate(this);
        Circuit c = new Circuit2();
        // inicializáljuk az áramkört
        c.init();
        // beregisztráljuk a szimulációnál
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsoló és egy led van összekötve.
     */
    private static class Circuit2 extends Circuit {

        @Override
        public void init() {
            // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
            // majd létrehozuk egy wire1 vezetéket "toggle_to_inv" névvel
            Logger.logCall(this, "init");
            Wire wire1 = new Wire("toggle_to_inv");

            // létrehozunk egy kapcsolót, és kimenetére a fenti wire1 vezetéket kötjük
            Toggle toggle = new Toggle("toggle");
            toggle.setOutput(0, wire1);

            // Létrehozunk egy újabb wire2 vezetéket "inv_to_led" névvel
            Wire wire2 = new Wire("inv_to_led");

            // létrehozunk egy inv invertert "inv" névvel
            // majd bemenetére a wire1 vezetéket kötjük
            Inverter inv = new Inverter("inv");
            inv.setInput(0, wire1);
            // kimenetére a wire2-t.
            inv.setOutput(0, wire2);

            // létrehozunk egy ledet "led" névvel
            // majd bemenetére az inverter kimenetére kötött wire2 vezetéket kötjük
            Led led = new Led("led");
            led.setInput(0, wire2);

            // majd hozzáadjuk az áramkörhöz a kapcsolót, az invertert és a ledet
            toggle.addTo(this);
            inv.addTo(this);
            led.addTo(this);

            // utasítjuk a loggert, hogy jelezze hogy függvény vége (RETURN)
            Logger.logReturn();
        }
    }
}
