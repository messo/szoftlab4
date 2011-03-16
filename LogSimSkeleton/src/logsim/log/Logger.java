package logsim.log;

import java.io.IOException;
import logsim.model.Value;

/**
 * Loggol�st seg�t� oszt�ly
 *
 */
public class Logger {

    /**
     * Beh�z�s m�rt�ke
     */
    public static int indent = 0;

    /**
     * Param�terben megadott sz�veget a jelenlegi beh�z�ssal ki�rja
     * @param s Ki�rand� sz�veg
     */
    private static void print(String s) {
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
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < indent; i++) {
//            sb.append("  ");
//        }
//        sb.append(s);
        print(s);
        System.out.println();
//        System.out.println(sb.toString());
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
     * �res visszat�r�s
     */
    public static void logReturn() {
        logReturn(null);
    }

    /**
     * P�ld�nyos�t�s ki�r�sa
     * @param obj Objektum
     */
    public static void logCreate(Loggable obj) {
        println(String.format("CREATE %s %s", obj.getClassName(), obj.getName()));
        indent++;
    }

    private static Integer logAsk(Loggable obj, String question) {
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
        return ch;
    }

    public static Value logAskValue(Loggable obj, String question) {
//        print("QUESTION " + obj.getName() + " " + question + "? [0/1] ");
//
//        int ch;
//        while (true) {
//            try {
//                ch = System.in.read();
//                if (ch == '0' || ch == '1') {
//                    break;
//                }
//            } catch (IOException ex) {
//                return null;
//            }
//        }
        Integer ch = Logger.logAsk(obj, question);
        if (ch == '0') {
            return Value.FALSE;
        } else {
            return Value.TRUE;
        }
    }

    public static Boolean logAskBool(Loggable obj, String question) {
//        print("QUESTION " + obj.getName() + " " + question + "? [0/1] ");
//
//        int ch;
//        while (true) {
//            try {
//                ch = System.in.read();
//                if (ch == '0' || ch == '1') {
//                    break;
//                }
//            } catch (IOException ex) {
//                return null;
//            }
//        }
        Integer ch = Logger.logAsk(obj, question);

        if (ch == '0') {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Visszat�rt�s param�terrel
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
     * Kommentet �r a megjelen�t�re
     * @param string Komment
     */
    public static void logComment(String string) {
        println("# " + string);
    }
}
