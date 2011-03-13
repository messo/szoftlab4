package logsim.log;

import java.io.IOException;
import java.util.logging.Level;
import logsim.model.Value;

/**
 *
 * @author balint
 */
public class Logger {

    public static int indent = 0;

    private static void print(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        sb.append(s);
        System.out.println(sb.toString());
    }

    public static void logCall(Loggable obj, String method, Loggable... params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(params[i].getName());
        }
        print(String.format("CALL %s.%s(%s)", obj.getName(), method, sb.toString()));
        indent++;
    }

    public static void logReturn() {
        logReturn(null);
    }

    public static void logCreate(Loggable obj) {
        print(String.format("CREATE %s %s", obj.getClassName(), obj.getName()));
        indent++;
    }

    public static Value logAskValue(Loggable obj, String question) {
        print("QUESTION " + obj.getName() + " " + question + "? [0/1]");

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
            return Value.FALSE;
        } else {
            return Value.TRUE;
        }
    }

    public static Boolean logAskBool(Loggable obj, String question) {
        print("QUESTION " + obj.getName() + " " + question + "? [0/1]");

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

    public static void logReturn(String string) {
        indent--;
        if (string == null) {
            print("RETURN");
        } else {
            print("RETURN " + string);
        }
    }

    public static void logComment(String string) {
        print("# " + string);
    }
}
