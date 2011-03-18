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

        //Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
        //majd l�trehozuk egy wire1 vezet�ket "toggle_to_inv" n�vvel
        Logger.logCall(this, "init");
        Wire wire1 = new Wire("toggle_to_inv");

        //l�trehozunk egy kapcsol�t, �s kimenet�re a fenti wire1 vezet�ket k�tj�k
        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, wire1);
        
        //l�trehozunk egy inv invertert "inv" n�vvel
        //majd bemenet�re a wire egy vezet�ket k�tj�k
        Inverter inv = new Inverter("inv");
        inv.setInput(0, wire1);
        
        //L�trehozunk egy �jabb wire2 vezet�ket "inv_to_led" n�vvel
        Wire wire2 = new Wire("inv_to_led");
        
        //majd az inverter kimenet�re k�tj�k
        inv.setOutput(0, wire2);
        
        //l�trehozunk egy ledet "led" n�vvel
        //majd bemenet�re az inverterb�l kij�v� vezet�ket k�tj�k
        Led led = new Led("led");
        led.setInput(0, wire2);

        //majd hozz�adjuk az �ramk�rh�z a kapcsol�t invertert �s ledet
        toggle.addTo(this);
        inv.addTo(this);
        led.addTo(this);
        
        //utas�tjuk a loggert, hogy jelezze hogy f�ggv�ny v�ge (RETURN)
        Logger.logReturn();
    }
}
