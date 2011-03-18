package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit1 extends Circuit {

    @Override
    public void init() {
        
        //Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
        //majd l�trehozuk egy "wire" nev� vezet�k elemet
        Logger.logCall(this, "init");
        Wire wire = new Wire("wire");
        

        //L�trehozunk egy "toggle" nev� kapcsol� elemet
        //majd a kimenet�re r�k�tj�k az el�z�leg l�trehozott vezet�ket
        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, wire);
        
        //L�trehozunk egy "led" nev� kijelz�(led) elemet
        //majd a bemenet�re a fent l�trehozott vezet�ket k�tj�k
        Led led = new Led("led");
        led.setInput(0, wire);

        //hozz�adjuk az �ramk�rh�z a l�trehozott kapcsol�t �s ledet
        toggle.addTo(this);
        led.addTo(this);
        
        //utas�tjuk a logger-t hogy �rja ki, hogy a f�ggv�nynek v�ge, azaz visszat�r(RETURN)
        Logger.logReturn();
    }
}
