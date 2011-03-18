package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.component.Wire;
import logsim.model.component.impl.Inverter;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;

/**
 *
 * @author Balint
 */
public class Circuit4 extends Circuit {

    @Override
    public void init() {
        
        //Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
        Logger.logCall(this, "init");

        //l�trehozunk 3 vezet�ket "inv_to_node","inv_to_inv","node_to_led" n�vvel
        Wire inv_to_node = new Wire("inv_to_node");
        Wire inv_to_inv = new Wire("inv_to_inv");
        Wire node_to_led = new Wire("node_to_led");
        
        //l�trehozunk egy invertert
        //bemenet�re az inv_to_inv vezet�ket, (azaz visszacsatoljuk)
        //kimenet�re az inv_to_node vezet�ket k�tj�k
        Inverter t = new Inverter("inverter");
        t.setInput(0, inv_to_inv);
        t.setOutput(0, inv_to_node);

        //l�trehozunk egy csom�pontot, mely 2 ir�nyba el�gazik
        //bemenet�re, (azaz az inv_to_node vezet�k �gazik) az inv_to_node-t �ll�tjuk
        //kimenet�re a m�sik k�t vezet�ket �ll�tjuk, ezen a 2 vezet�ken megy tov�bb az �ramk�r
        Node n = new Node(2, "node");
        n.setInput(0, inv_to_node);
        n.setOutput(0, inv_to_inv);
        n.setOutput(1, node_to_led);

        //l�trehozunk egy ledet, �s bemenet�re a node_to_led vezet�ket k�tj�k
        Led l = new Led("led");
        l.setInput(0, node_to_led);
        
        //hozz�adjuk a h�l�zathoz az invertert, a csom�pontot �s a ledet
        t.addTo(this);
        n.addTo(this);
        l.addTo(this);
        
        //utas�tjuk a logger-t hogy �rja ki, hogy a f�ggv�nynek v�ge, azaz visszat�r(RETURN)
        Logger.logReturn();
    }
}
