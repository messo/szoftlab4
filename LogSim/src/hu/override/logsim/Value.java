package hu.override.logsim;

/**
 * Az áramkörben elõfordulható érték
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

        // ilyen nem lehet, de lehet lesz másik típus
        return null;
    }

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
