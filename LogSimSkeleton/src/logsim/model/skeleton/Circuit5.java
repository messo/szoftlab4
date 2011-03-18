package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Balint
 */
public class Circuit5 extends Circuit {

    @Override
    public void init() {

        //Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
        Logger.logCall(this, "init");
        
        //l�trehozunk egy vezet�ket, ami a kapcsol�b�l a vagykapuba megy
        Wire toggle_to_orgate = new Wire("toggle_to_orgate");

        //l�trehozzuk a kapcsol�t, �s kimenet�re r�k�tj�k az el�z� vezet�ket
        Toggle toggle = new Toggle("toggle");
        toggle.setOutput(0, toggle_to_orgate);

        //l�trehozunk egy �jabb vezet�ket, ami a vagykapub�l a csom�pontba megy
        Wire orgate_to_node = new Wire("orgate_to_node");

        //l�trehozunk egy 2 bemenet� vagykaput
        //egyik bemenet�re a kapcsol�b�l j�v� vezet�ket k�tj�k
        //kimenet�re a csom�pontba men� vezet�ket k�tj�k
        OrGate orgate = new OrGate(2, "orgate");
        orgate.setInput(0, toggle_to_orgate);
        orgate.setOutput(0, orgate_to_node);

        //l�trehozzuk a 2 fel� �gaz� csom�pontot
        //r�k�tj�k a vagykapub�l kij�v� vezet�kre
        Node n = new Node(2, "node");
        n.setInput(0, orgate_to_node);

        //l�trehozunk egy visszacsatol� vezet�ket
        //�s be�ll�tjuk, hogy a csom�pontb�l vissza�gazzon a vagykapuba
        //�s egy�ttal be�ll�tjuk a vagykapu bemenet�re is ezt a vezet�ket
        Wire node_to_orgate = new Wire("node_to_orgate");
        n.setOutput(0, node_to_orgate);
        orgate.setInput(1, node_to_orgate);

        //l�trehozunk egy m�sik vezet�ket ami a csom�pont m�sik �g�b�l j�n
        //be�ll�tjuk a csom�pont kimenet�re
        Wire node_to_led = new Wire("node_to_led");
        n.setOutput(1, node_to_led);

        //l�trehozunk egy ledet, aminek bemenet�re az el�z� vezet�ket k�tj�k
        Led l = new Led("led");
        l.setInput(0, node_to_led);

        //hozz�adjuk az �ramk�rh�z az elemeket
        toggle.addTo(this);
        orgate.addTo(this);
        n.addTo(this);
        l.addTo(this);
        
        //utas�tjuk a logger-t hogy �rja ki, hogy a f�ggv�nynek v�ge, azaz visszat�r(RETURN)
        Logger.logReturn();
    }
}
