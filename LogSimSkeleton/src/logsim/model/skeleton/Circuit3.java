package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * 3. teszteset, ahol 2 kapcsol�, egy VAGY kapu �s egy led van �sszek�tve.
 */
public class Circuit3 extends Circuit {

    @Override
    public void init() {
        // Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
        Logger.logCall(this, "init");

        // l�trehozunk egy wire1 vezet�ket "toggle1_to_orgate" n�vvel
        Wire wire1 = new Wire("toggle1_to_orgate");

        // l�trehozunk egy toggle1 kapcsol�t "toggle1" n�vvel
        // �s kimenet�re k�tj�k az el�z� vezet�ket
        Toggle toggle1 = new Toggle("toggle1");
        toggle1.setOutput(0, wire1);

        // l�trehozunk egy �jabb vezet�ket
        Wire wire2 = new Wire("toggle2_to_orgate");

        // �s egy �jabb kapcsol�t
        // majd a kapcsol� kimenet�re a wire2 vezet�ket k�tj�k
        Toggle toggle2 = new Toggle("toggle2");
        toggle2.setOutput(0, wire2);

        // l�trehozunk egy harmadik vezet�ket
        Wire wire3 = new Wire("orgate_to_led");

        // l�trehozunk egy VAGYkaput "orgate" n�vvel 2 bemenettel
        // majd bemeneteire az el�z�leg l�trehozott 2 vezet�ket (wire1, wire2) k�tj�k
        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, wire1);
        orgate.setInput(1, wire2);
        // kimenet�re pedig az utolj�ra l�trehozott vezet�ket k�tj�k
        orgate.setOutput(0, wire3);

        // l�trehozunk egy ledet, aminek a bemenete a vagykapu kimenetre k�t�tt wire3 vezet�k
        Led led = new Led("led");
        led.setInput(0, wire3);

        // hozz�adjuk az �ramk�rh�z a k�t kapcsol�t, a vagykaput �s a ledet
        toggle1.addTo(this);
        toggle2.addTo(this);
        orgate.addTo(this);
        led.addTo(this);

        // utas�tjuk a logger-t hogy �rja ki, hogy a f�ggv�nynek v�ge, azaz visszat�r(RETURN)
        Logger.logReturn();
    }
}
