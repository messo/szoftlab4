package logsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import logsim.model.Simulation;
import logsim.model.skeleton.Circuit1;
import logsim.model.skeleton.Circuit2;
import logsim.model.skeleton.Circuit3;
import logsim.model.skeleton.Circuit4;
import logsim.model.skeleton.Circuit5;

/**
 * Skeleton main osztály. Ez tartalmazza az indító logikát.
 */
public class Skeleton {

    private static final String KAPCSOLO_LED = "1";
    private static final String KAPCSOLO_INVERTER_LED = "2";
    private static final String KAPCSOLO_2x_VAGY_LED = "3";
    private static final String INVERTER_VISSZAKOTVE_LED = "4";
    private static final String VAGY_VISSZAKOTVE_LED = "5";
    private static final String EXIT = "0";
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    //kimeneti adatfolyam, erre írunk
    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, "CP852"), true);
        } catch (Exception e) {
            System.out.println("Outstream error!");
            System.exit(-1);
        }
    }
    /**
     * Kiírja a kijelzõre a menüpontokat
     */
    private void printMenu() {
        out.println("Kérlek válassz az alábbi menüpontok közül:");
        out.println("");
        out.println("(1) Kapcsoló és led szimulációja");
        out.println("     Egy darab kapcsoló, melyre led van kötve");
        out.println("(2) Kapcsoló, inverter és led szimulációja");
        out.println("     Egy darab kapcsoló, melyre egy inverter van kötve, és ennek kimenete van");
        out.println("     egy leden.");
        out.println("(3) 2 kapcsoló, VAGY kapu és led szimulációja");
        out.println("     Két darab kapcsoló, melyek egy 2 bemenetes VAGY kapura vannak kötve, ennek");
        out.println("     kimenete pedig egy ledre.");
        out.println("(4) Visszakötött inverter szimulációja");
        out.println("     Egy inverter, melynek kimenete egy ledre és saját maga bemenetére van kötve");
        out.println("(5) Kapcsoló, visszakötött VAGY kapu és led szimulációja");
        out.println("     Egy 2 bemenetes VAGY kapu, melynek egyik bemenetére egy kapcsoló, másikra");
        out.println("     a saját bemenete van kötve.");
        out.println("");
        out.println("(0) Kilépés");
        out.println("");
        out.print("Választott menüpont (írd be a számot, majd ENTER): ");
        out.flush();
    }

    public Skeleton() {
        boolean shouldRun = true;
        String str;
        
        //Felhasználótól bekérjük, hogy melyik áramkört szeretné tesztelni
        while (shouldRun) {
            try {
                printMenu();
                str = keyboard.readLine();
                if (str.equals(KAPCSOLO_LED)) {
                    testKapcsoloLed();
                } else if (str.equals(KAPCSOLO_INVERTER_LED)) {
                    testKapcsoloInverterLed();
                } else if (str.equals(KAPCSOLO_2x_VAGY_LED)) {
                    testKapcsolo2xVagy();
                } else if (str.equals(INVERTER_VISSZAKOTVE_LED)) {
                    testInverterVisszakotveLed();
                } else if (str.equals(VAGY_VISSZAKOTVE_LED)) {
                    testVagyVisszakotveLed();
                } else if (str.equals(EXIT)) {
                    return;
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                return;
            }
        }
    }
    
    //A választásnak megfelelõ áramköröket létrehozó, és elindító függvények:
    //mindegyik létrehoz egy szimulációt, egy a kiválasztott tesztáramkört
    //beállítja szimulálásra a létrehozott áramkört, inicializálja, és indítja    

    private void testKapcsoloLed() {
        Simulation simulation = new Simulation();
        Circuit1 c = new Circuit1();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testKapcsoloInverterLed() {
        Simulation simulation = new Simulation();
        Circuit2 c = new Circuit2();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testKapcsolo2xVagy() {
        Simulation simulation = new Simulation();
        Circuit3 c = new Circuit3();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testInverterVisszakotveLed() {
        Simulation simulation = new Simulation();
        Circuit4 c = new Circuit4();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    private void testVagyVisszakotveLed() {
        Simulation simulation = new Simulation();
        Circuit5 c = new Circuit5();
        simulation.setCircuit(c);
        c.init();
        simulation.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }
}
