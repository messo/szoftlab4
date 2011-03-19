package logsim.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import logsim.model.Value;

/**
 * Loggol�st seg�t� oszt�ly
 */
public class Logger {

    /**
     * Beh�z�s m�rt�ke
     */
    private static int indent = 0;
    /**
     * Loggol�s enged�lyezett flagje
     */
    private static boolean enabled = true;
    /**
     * Bemenet, innen olvassuk a felhaszn�l� v�laszait
     */
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Kimeneti adatfolyam, ide �runk
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
     * Loggol�s bekapcsol�sa
     */
    public static void turnOn() {
        enabled = true;
    }

    /**
     * Loggol�s kikapcsol�sa
     */
    public static void turnOff() {
        enabled = false;
    }

    /**
     * Param�terben megadott sz�veget a jelenlegi beh�z�ssal ki�rja.
     * 
     * @param s Ki�rand� sz�veg
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
        out.print(sb.toString());
    }

    /**
     * Param�terben megadott sz�veget a jelenlegi beh�z�ssal ki�rja majd �j sorra
     * �ll�tja a kurzort.
     * 
     * @param s Ki�rand� sz�veg
     */
    private static void println(String s) {
        if (!enabled) {
            return;
        }

        print(s);
        out.println();
    }

    /**
     * F�ggv�ny h�v�s ki�r�sa
     * 
     * @param obj Melyik objektumon
     * @param method Melyik met�dust
     * @param params Milyen param�terekkel
     */
    public static void logCall(Loggable obj, String method, Loggable... params) {
        StringBuilder sb = new StringBuilder();
        // param�terek �sszef�z�se
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(params[i].getName());
        }
        // ki�rjuk
        println(String.format("CALL %s.%s(%s)", obj.getName(), method, sb.toString()));
        // beh�z�st n�velj�k
        indent++;
    }

    /**
     * Visszat�r�s param�ter n�lk�l
     */
    public static void logReturn() {
        logReturn(null);
    }

    /**
     * Visszat�r�s param�terrel
     * 
     * @param string Param�ter
     */
    public static void logReturn(String string) {
        // beh�z�st cs�kkentj�k
        indent--;
        if (string == null) {
            println("RETURN");
        } else {
            println("RETURN " + string);
        }
    }

    /**
     * P�ld�nyos�t�s ki�r�sa
     * 
     * @param obj Objektum
     */
    public static void logCreate(Loggable obj) {
        println(String.format("CREATE %s %s", obj.getClassName(), obj.getName()));
        indent++;
    }

    /**
     * Felhaszn�l�t�l bek�r�nk egy Value-t.
     *
     * @param obj melyik objektum k�ri
     * @param question mi a k�rd�s
     * @return felhaszn�l� �ltal megadott Value
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
     * Felhaszn�l�t�l bek�r�nk egy Booleant.
     *
     * @param obj melyik objektum k�ri
     * @param question mi a k�rd�s
     * @return felhaszn�l� �ltal megadott Boolean
     */
    public static Boolean logAskBool(Loggable obj, String question) {
        print("QUESTION " + obj.getName() + " " + question + "? [0/1] ");
        out.flush();
        int ch;
        String str;
        while (true) {
            try {
                // beolvasunk egy sort
                str = keyboard.readLine();
                // annak az els� karaktere a fontos
                ch = str.charAt(0);
                if (ch == '0' || ch == '1') {
                    break;
                }
            } catch (IOException ex) {
                out.println("Nem tudtuk beolvasni a v�laszt!");
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
     * Megjegyz�s ki�r�sa
     *
     * @param string Komment
     */
    public static void logComment(String string) {
        println("# " + string);
    }
}
