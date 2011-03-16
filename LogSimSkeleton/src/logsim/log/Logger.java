package logsim.log;

import java.io.IOException;
import logsim.model.Value;

/**
 * Loggolást segítõ osztály
 */
public class Logger {

    /**
     * Behúzás mértéke
     */
    private static int indent = 0;
    /**
     * Loggolás engedélyezett flagje
     */
    private static boolean enabled = true;

    /**
     * Loggolás bekapcsolása
     */
    public static void turnOn() {
        enabled = true;
    }

    /**
     * Loggolás kikapcsolása
     */
    public static void turnOff() {
        enabled = false;
    }

    /**
     * Paraméterben megadott szöveget a jelenlegi behúzással kiírja
     * @param s Kiírandó szöveg
     */
    private static void print(String s) {
        if (!enabled) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        sb.append(s);
        System.out.print(sb.toString());
    }

    /**
     * Paraméterben megadott szöveget a jelenlegi behúzással kiírja majd új sorra
     * állítja a kurzort
     * @param s Kiírandó szöveg
     */
    private static void println(String s) {
        if (!enabled) {
            return;
        }

        print(s);
        System.out.println();
    }

    /**
     * Függvény hívás kiírása
     * @param obj Melyik objektumon
     * @param method Melyik metódust
     * @param params Milyen paraméterekkel
     */
    public static void logCall(Loggable obj, String method, Loggable... params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(params[i].getName());
        }
        println(String.format("CALL %s.%s(%s)", obj.getName(), method, sb.toString()));
        indent++;
    }

    /**
     * Visszatérés paraméter nélkül
     */
    public static void logReturn() {
        logReturn(null);
    }

    /**
     * Visszatérés paraméterrel
     * 
     * @param string Paraméter
     */
    public static void logReturn(String string) {
        indent--;
        if (string == null) {
            println("RETURN");
        } else {
            println("RETURN " + string);
        }
    }

    /**
     * Példányosítás kiírása
     * @param obj Objektum
     */
    public static void logCreate(Loggable obj) {
        println(String.format("CREATE %s %s", obj.getClassName(), obj.getName()));
        indent++;
    }

    /**
     * Felhasználótól bekérünk egy Value-t.
     *
     * @param obj melyik objektum kéri
     * @param question mi a kérdés
     * @return felhasználó által megadott Value
     */
    public static Value logAskValue(Loggable obj, String question) {
        Boolean bool = logAskBool(obj, question);
        if (bool == null) {
            return null;
        }

        if (bool) {
            return Value.TRUE;
        } else {
            return Value.FALSE;
        }
    }

    /**
     * Felhasználótól bekérünk egy Booleant.
     *
     * @param obj melyik objektum kéri
     * @param question mi a kérdés
     * @return felhasználó által megadott Boolean
     */
    public static Boolean logAskBool(Loggable obj, String question) {
        print("QUESTION " + obj.getName() + " " + question + "? [0/1] ");

        int ch;
        while (true) {
            try {
                ch = System.in.read();
                if (ch == '0' || ch == '1') {
                    break;
                }
            } catch (IOException ex) {
                return null;
            }
        }

        if (ch == '0') {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Kommentet ír a megjelenítõre
     *
     * @param string Komment
     */
    public static void logComment(String string) {
        println("# " + string);
    }
}
