package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit2 extends Circuit {

    @Override
    public void init() {

        //Loggert utasítjuk, hogy írja ki, h az init függvény hívása történt
        //majd létrehozuk egy wire1 vezetéket "toggle_to_inv" névvel
        Logger.logCall(this, "init");
        Wire wire1 = new Wire("toggle_to_inv");

        //létrehozunk egy kapcsolót, és kimenetére a fenti wire1 vezetéket kötjük
        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, wire1);
        
        //létrehozunk egy inv invertert "inv" névvel
        //majd bemenetére a wire egy vezetéket kötjük
        Inverter inv = new Inverter("inv");
        inv.setInput(0, wire1);
        
        //Létrehozunk egy újabb wire2 vezetéket "inv_to_led" névvel
        Wire wire2 = new Wire("inv_to_led");
        
        //majd az inverter kimenetére kötjük
        inv.setOutput(0, wire2);
        
        //létrehozunk egy ledet "led" névvel
        //majd bemenetére az inverterbõl kijövõ vezetéket kötjük
        Led led = new Led("led");
        led.setInput(0, wire2);

        //majd hozzáadjuk az áramkörhöz a kapcsolót invertert és ledet
        toggle.addTo(this);
        inv.addTo(this);
        led.addTo(this);
        
        //utasítjuk a loggert, hogy jelezze hogy függvény vége (RETURN)
        Logger.logReturn();
    }
}
