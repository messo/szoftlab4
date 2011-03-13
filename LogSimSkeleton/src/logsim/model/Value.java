package logsim.model;

import logsim.log.Loggable;

/**
 * Az áramkörben elõfordulható értéket reprezentál.
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

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String getClassName() {
        return "Value";
    }
}
