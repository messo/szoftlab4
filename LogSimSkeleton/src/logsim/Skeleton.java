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
 * Skeleton main oszt�ly. Ez tartalmazza az ind�t� logik�t.
 */
public class Skeleton {

    private static final String KAPCSOLO_LED = "1";
    private static final String KAPCSOLO_INVERTER_LED = "2";
    private static final String KAPCSOLO_2x_VAGY_LED = "3";
    private static final String INVERTER_VISSZAKOTVE_LED = "4";
    private static final String VAGY_VISSZAKOTVE_LED = "5";
    private static final String EXIT = "0";
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    //kimeneti adatfolyam, erre �runk
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
     * Ki�rja a kijelz�re a men�pontokat
     */
    private void printMenu() {
        out.println("K�rlek v�lassz az al�bbi men�pontok k�z�l:");
        out.println("");
        out.println("(1) Kapcsol� �s led szimul�ci�ja");
        out.println("     Egy darab kapcsol�, melyre led van k�tve");
        out.println("(2) Kapcsol�, inverter �s led szimul�ci�ja");
        out.println("     Egy darab kapcsol�, melyre egy inverter van k�tve, �s ennek kimenete van");
        out.println("     egy leden.");
        out.println("(3) 2 kapcsol�, VAGY kapu �s led szimul�ci�ja");
        out.println("     K�t darab kapcsol�, melyek egy 2 bemenetes VAGY kapura vannak k�tve, ennek");
        out.println("     kimenete pedig egy ledre.");
        out.println("(4) Visszak�t�tt inverter szimul�ci�ja");
        out.println("     Egy inverter, melynek kimenete egy ledre �s saj�t maga bemenet�re van k�tve");
        out.println("(5) Kapcsol�, visszak�t�tt VAGY kapu �s led szimul�ci�ja");
        out.println("     Egy 2 bemenetes VAGY kapu, melynek egyik bemenet�re egy kapcsol�, m�sikra");
        out.println("     a saj�t bemenete van k�tve.");
        out.println("");
        out.println("(0) Kil�p�s");
        out.println("");
        out.print("V�lasztott men�pont (�rd be a sz�mot, majd ENTER): ");
        out.flush();
    }

    public Skeleton() {
        boolean shouldRun = true;
        String str;
        
        //Felhaszn�l�t�l bek�rj�k, hogy melyik �ramk�rt szeretn� tesztelni
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
    
    //A v�laszt�snak megfelel� �ramk�r�ket l�trehoz�, �s elind�t� f�ggv�nyek:
    //mindegyik l�trehoz egy szimul�ci�t, egy a kiv�lasztott teszt�ramk�rt
    //be�ll�tja szimul�l�sra a l�trehozott �ramk�rt, inicializ�lja, �s ind�tja    

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
