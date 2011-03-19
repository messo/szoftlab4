package logsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import logsim.log.Logger;
import logsim.model.Simulation;
import logsim.model.skeleton.Simulation1;

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
    /**
     * Bemenet, innen olvassuk a felhaszn�l� v�laszt�sait
     */
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Kimeneti adatfolyam, ide �runk.
     */
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

    /**
     * A program indul�sakor, megk�ri a felhaszn�l�t, hogy v�lasszon, ha v�lasztott
     * lefuttatja a megfelel� teszteset vagy kil�p. Ezt addig ism�tli, am�g a felhaszn�l�
     * nem akar kil�pni.
     */
    public Skeleton() {
        boolean shouldRun = true;
        String str;

        while (shouldRun) {
            try {
                // ki�rjuk a men�t
                printMenu();
                // Felhaszn�l�t�l bek�rj�k, hogy melyik �ramk�rt szeretn� tesztelni
                str = keyboard.readLine();
                if (str.equals(KAPCSOLO_LED)) {
                    test(1);
                } else if (str.equals(KAPCSOLO_INVERTER_LED)) {
                    test(2);
                } else if (str.equals(KAPCSOLO_2x_VAGY_LED)) {
                    test(3);
                } else if (str.equals(INVERTER_VISSZAKOTVE_LED)) {
                    test(4);
                } else if (str.equals(VAGY_VISSZAKOTVE_LED)) {
                    test(5);
                } else if (str.equals(EXIT)) {
                    return;
                }
            } catch (IOException ex) {
                out.println("Valami I/O hiba van!");
                return;
            }
        }
    }

    /**
     * Az adott tesztesetet lefuttatja.
     *
     * @param testCase
     */
    private void test(int testCase) {
        String str;
        char ch = '1';
        // megk�rdezz�k a felhaszn�l�t, hogy ki�rjuk-e az initet.
        try {
            while (true) {
                out.print("Szeretn�d l�tni az inicializ�l�st? [0/1] ");
                out.flush();
                str = keyboard.readLine();
                ch = str.charAt(0);
                if (ch == '1' || ch == '0') {
                    // ha 1-et vagy 0-�t v�laszolt, akkor k�sz.
                    break;
                }
            }
        } catch (IOException ex) {
            out.println("Nem siker�lt beolvasni a felhaszn�l�t�l (Alap�rtelmezett v�lasz: 1)!");
        }

        if (ch == '0') {
            // ha azt mondta, hogy nem, akkor kikapcsoljuk!
            Logger.turnOff();
        } else {
            // biztos, ami biztos.
            Logger.turnOn();
        }

        out.println();
        out.println("--------- TESZT KEZDETE ---------");
        out.println();

        Logger.logComment("Inicializ�l�s");
        Simulation s = null;
        // megfelel� tesztesetnek megfelel�en, l�trehozzuk a szimul�ci�t
        switch (testCase) {
            case 1:
                s = new Simulation1();
                break;
            case 2:
                s = new Simulation();
                break;
            case 3:
                s = new Simulation();
                break;
            case 4:
                s = new Simulation();
                break;
            case 5:
                s = new Simulation();
                break;
        }

        // Innent�l mindenk�pp kell loggol�s!
        Logger.turnOn();
        Logger.logComment("Szimul�ci�");
        // szimul�ci� ind�t�sa!
        s.start();

        out.println();
        out.println("---------- TESZT V�GE -----------");
        out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }
}
