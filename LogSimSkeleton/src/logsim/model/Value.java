package logsim.model;

import logsim.log.Loggable;

/**
 * Az �ramk�rben el�fordulhat� �rt�ket reprezent�l.
 *
 * @author balint
 */
public enum Value implements Loggable {

    TRUE, FALSE;

    @Override
    public String toString() {
        if (this == TRUE) {
            return "Value.TRUE";
        } else if (this == FALSE) {
            return "Value.FALSE";
        }

        // ilyen nem lehet, de lehet lesz m�sik t�pus
        return null;
    }

    /**
     * Invert�lja az adott �rt�ket. Ennek addig van �rtelme, am�g 2 f�le
     * �llapot fordulhat el� a rendszerben.
     *
     * @return
     */
    public Value invert() {
        if (this == TRUE) {
            return FALSE;
        }
        if (this == FALSE) {
            return TRUE;
        }

        return null;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String getClassName() {
        return "Value";
    }
}
