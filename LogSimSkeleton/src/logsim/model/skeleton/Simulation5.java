package logsim.model.skeleton;

import logsim.log.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.component.Wire;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.OrGate;
import logsim.model.component.impl.Toggle;

/**
 * 5. tesztesethez tartoz� szimul�ci�
 */
public class Simulation5 extends Simulation {

    public Simulation5() {
        Logger.logCreate(this);
        Circuit c = new Circuit5();
        // inicializ�ljuk az �ramk�rt
        c.init();
        // beregisztr�ljuk a szimul�ci�n�l
        setCircuit(c);
        Logger.logReturn();
    }

    /**
     * 1. teszteset, amikor egy kapcsol� �s egy led van �sszek�tve.
     */
    private static class Circuit5 extends Circuit {

        @Override
        public void init() {
            // Loggert utas�tjuk, hogy �rja ki, h az init f�ggv�ny h�v�sa t�rt�nt
            Logger.logCall(this, "init");

            // l�trehozunk egy vezet�ket, ami a kapcsol�b�l a vagykapuba megy
            Wire toggle_to_orgate = new Wire("toggle_to_orgate");

            // l�trehozzuk a kapcsol�t, �s kimenet�re r�k�tj�k az el�z� vezet�ket
            Toggle toggle = new Toggle("toggle");
            toggle.setOutput(0, toggle_to_orgate);

            // l�trehozunk 3 vezet�ket
            Wire orgate_to_node = new Wire("orgate_to_node");
            Wire node_to_orgate = new Wire("node_to_orgate");
            Wire node_to_led = new Wire("node_to_led");

            // l�trehozunk egy 2 bemenet� vagykaput
            // egyik bemenet�re a kapcsol�b�l j�v� vezet�ket (toggle_to_orgate) k�tj�k
            // m�sikra a csom�pontb�l j�v� egyik vezet�ket (visszacsatolt vezet�k)
            // kimenet�re a csom�pontba men� vezet�ket (orgate_to_node) k�tj�k
            OrGate orgate = new OrGate(2, "orgate");
            orgate.setInput(0, toggle_to_orgate);
            orgate.setInput(1, node_to_orgate);
            orgate.setOutput(0, orgate_to_node);

            // l�trehozzuk a 2 fel� �gaz� csom�pontot
            // bemenet�re r�k�tj�k a vagykapub�l kij�v� vezet�ket
            Node n = new Node(2, "node");
            n.setInput(0, orgate_to_node);
            // kimeneteire pedig a vagy kapuba men�t (node_to_orgate)
            // illetve a ledhez men�t (node_to_led) k�tj�k
            n.setOutput(0, node_to_orgate);
            n.setOutput(1, node_to_led);

            // l�trehozunk egy ledet, aminek bemenet�re a node_to_led-et k�tj�k
            Led l = new Led("led");
            l.setInput(0, node_to_led);

            // hozz�adjuk az �ramk�rh�z az elemeket
            toggle.addTo(this);
            orgate.addTo(this);
            n.addTo(this);
            l.addTo(this);

            // utas�tjuk a logger-t hogy �rja ki, hogy a f�ggv�nynek v�ge, azaz visszat�r(RETURN)
            Logger.logReturn();
        }
    }
}
