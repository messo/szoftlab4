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
 * Skeleton main osztály. Ez tartalmazza az indító logikát.
 */
public class Skeleton {

    private static final String KAPCSOLO_LED = "1";
    private static final String KAPCSOLO_INVERTER_LED = "2";
    private static final String KAPCSOLO_2x_VAGY_LED = "3";
    private static final String INVERTER_VISSZAKOTVE_LED = "4";
    private static final String VAGY_VISSZAKOTVE_LED = "5";
    private static final String EXIT = "0";
    /**
     * Bemenet, innen olvassuk a felhasználó választásait
     */
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Kimeneti adatfolyam, ide írunk.
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

    /**
     * A program indulásakor, megkéri a felhasználót, hogy válasszon, ha választott
     * lefuttatja a megfelelõ teszteset vagy kilép. Ezt addig ismétli, amíg a felhasználó
     * nem akar kilépni.
     */
    public Skeleton() {
        boolean shouldRun = true;
        String str;

        while (shouldRun) {
            try {
                // kiírjuk a menüt
                printMenu();
                // Felhasználótól bekérjük, hogy melyik áramkört szeretné tesztelni
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
        // megkérdezzük a felhasználót, hogy kiírjuk-e az initet.
        try {
            while (true) {
                out.print("Szeretnéd látni az inicializálást? [0/1] ");
                out.flush();
                str = keyboard.readLine();
                ch = str.charAt(0);
                if (ch == '1' || ch == '0') {
                    // ha 1-et vagy 0-át válaszolt, akkor kész.
                    break;
                }
            }
        } catch (IOException ex) {
            out.println("Nem sikerült beolvasni a felhasználótól (Alapértelmezett válasz: 1)!");
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

        Logger.logComment("Inicializálás");
        Simulation s = null;
        // megfelelõ tesztesetnek megfelelõen, létrehozzuk a szimulációt
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

        // Innentõl mindenképp kell loggolás!
        Logger.turnOn();
        Logger.logComment("Szimuláció");
        // szimuláció indítása!
        s.start();

        out.println();
        out.println("---------- TESZT VÉGE -----------");
        out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Skeleton();
    }
}
