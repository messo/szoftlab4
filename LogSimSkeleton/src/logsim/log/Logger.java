package logsim.log;

import java.io.IOException;
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
     * Param�terben megadott sz�veget a jelenlegi beh�z�ssal ki�rja
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
        System.out.print(sb.toString());
    }

    /**
     * Param�terben megadott sz�veget a jelenlegi beh�z�ssal ki�rja majd �j sorra
     * �ll�tja a kurzort
     * @param s Ki�rand� sz�veg
     */
    private static void println(String s) {
        if (!enabled) {
            return;
        }

        print(s);
        System.out.println();
    }

    /**
     * F�ggv�ny h�v�s ki�r�sa
     * @param obj Melyik objektumon
     * @param method Melyik met�dust
     * @param params Milyen param�terekkel
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
        indent--;
        if (string == null) {
            println("RETURN");
        } else {
            println("RETURN " + string);
        }
    }

    /**
     * P�ld�nyos�t�s ki�r�sa
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
     * Kommentet �r a megjelen�t�re
     *
     * @param string Komment
     */
    public static void logComment(String string) {
        println("# " + string);
    }
}
