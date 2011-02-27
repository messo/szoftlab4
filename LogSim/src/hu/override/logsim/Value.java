package hu.override.logsim;

/**
 * Az áramkörben elõfordulható értéket reprezentál.
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

    /**
     * Invertálja az adott értéket. Ennek addig van értelme, amíg 2 féle
     * állapot fordulhat elõ a rendszerben.
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
