package logsim.model;

import logsim.log.Loggable;

/**
 * Az áramkörben elõfordulható értéket reprezentál.
 *
 */
public enum Value implements Loggable {

    TRUE, FALSE;

    /**
     * A saját értékét szövegként adja vissza
     * @return Érték
     */
    @Override
    public String toString() {
        if (this == TRUE) {
            return "Value.TRUE";
        } else if (this == FALSE) {
            return "Value.FALSE";
        }

        return null;
    }

    /**
     * Invertálja az adott értéket. Ennek addig van értelme, amíg 2 féle
     * állapot fordulhat elõ a rendszerben.
     *
     * @return Invertált érték
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Value";
    }
}
