package logsim.log;

import java.io.IOException;
import logsim.model.Value;

/**
 * Loggolást segítõ osztály
 *
 */
public class Logger {

    /**
     * Behúzás mértéke
     */
    public static int indent = 0;

    /**
     * Paraméterben megadott szöveget a jelenlegi behúzással kiírja
     * @param s Kiírandó szöveg
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
     * Paraméterben megadott szöveget a jelenlegi behúzással kiírja majd új sorra
     * állítja a kurzort
     * @param s Kiírandó szöveg
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
     * Üres visszatérés
     */
    public static void logReturn() {
        logReturn(null);
    }

    /**
     * Példányosítás kiírása
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
     * Visszatértés paraméterrel
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
     * Kommentet ír a megjelenítõre
     * @param string Komment
     */
    public static void logComment(String string) {
        println("# " + string);
    }
}
