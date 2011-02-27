package hu.override.logsim;

/**
 * Az �ramk�rben el�fordulhat� �rt�ket reprezent�l.
 *
 * @author balint
 */
public enum Value {

    TRUE, FALSE;

    @Override
    public String toString() {
        if (this == TRUE) {
            return "true";
        } else if (this == FALSE) {
            return "false";
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
}
