package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * 3. teszteset, ahol 2 kapcsoló, egy VAGY kapu és egy led van összekötve.
 */
public class Circuit3 extends Circuit {

    @Override
    public void init() {
        // Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
        Logger.logCall(this, "init");

        // létrehozunk egy wire1 vezetéket "toggle1_to_orgate" névvel
        Wire wire1 = new Wire("toggle1_to_orgate");

        // létrehozunk egy toggle1 kapcsolót "toggle1" névvel
        // és kimenetére kötjük az elõzõ vezetéket
        Toggle toggle1 = new Toggle("toggle1");
        toggle1.setOutput(0, wire1);

        // létrehozunk egy újabb vezetéket
        Wire wire2 = new Wire("toggle2_to_orgate");

        // és egy újabb kapcsolót
        // majd a kapcsoló kimenetére a wire2 vezetéket kötjük
        Toggle toggle2 = new Toggle("toggle2");
        toggle2.setOutput(0, wire2);

        // létrehozunk egy harmadik vezetéket
        Wire wire3 = new Wire("orgate_to_led");

        // létrehozunk egy VAGYkaput "orgate" névvel 2 bemenettel
        // majd bemeneteire az elõzõleg létrehozott 2 vezetéket (wire1, wire2) kötjük
        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, wire1);
        orgate.setInput(1, wire2);
        // kimenetére pedig az utoljára létrehozott vezetéket kötjük
        orgate.setOutput(0, wire3);

        // létrehozunk egy ledet, aminek a bemenete a vagykapu kimenetre kötött wire3 vezeték
        Led led = new Led("led");
        led.setInput(0, wire3);

        // hozzáadjuk az áramkörhöz a két kapcsolót, a vagykaput és a ledet
        toggle1.addTo(this);
        toggle2.addTo(this);
        orgate.addTo(this);
        led.addTo(this);

        // utasítjuk a logger-t hogy írja ki, hogy a függvénynek vége, azaz visszatér(RETURN)
        Logger.logReturn();
    }
}
