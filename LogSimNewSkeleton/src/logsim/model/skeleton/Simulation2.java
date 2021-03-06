package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 * 2. tesztesethez tartoz� szimul�ci�
 */
public class Simulation2 extends Simulation {

    public Simulation2() {
        Logger.logCreate(this);
        Circuit c = new Circuit2();
        // inicializ�ljuk az �ramk�rt
        c.init();
        // beregisztr�ljuk a szimul�ci�n�l
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsol� �s egy led van �sszek�tve.
     */
    private static class Circuit2 extends Circuit {

        @Override
        public void init() {
            // Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
            // majd l�trehozuk egy wire1 vezet�ket "toggle_to_inv" n�vvel
            Logger.logCall(this, "init");
            Wire wire1 = new Wire("toggle_to_inv");

            // l�trehozunk egy kapcsol�t, �s kimenet�re a fenti wire1 vezet�ket k�tj�k
            Toggle toggle = new Toggle("toggle");
            toggle.setOutput(0, wire1);

            // L�trehozunk egy �jabb wire2 vezet�ket "inv_to_led" n�vvel
            Wire wire2 = new Wire("inv_to_led");

            // l�trehozunk egy inv invertert "inv" n�vvel
            // majd bemenet�re a wire1 vezet�ket k�tj�k
            Inverter inv = new Inverter("inv");
            inv.setInput(0, wire1);
            // kimenet�re a wire2-t.
            inv.setOutput(0, wire2);

            // l�trehozunk egy ledet "led" n�vvel
            // majd bemenet�re az inverter kimenet�re k�t�tt wire2 vezet�ket k�tj�k
            Led led = new Led("led");
            led.setInput(0, wire2);

            // majd hozz�adjuk az �ramk�rh�z a kapcsol�t, az invertert �s a ledet
            toggle.addTo(this);
            inv.addTo(this);
            led.addTo(this);

            // utas�tjuk a loggert, hogy jelezze hogy f�ggv�ny v�ge (RETURN)
            Logger.logReturn();
        }
    }
}
