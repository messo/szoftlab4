package logsim.model;

/**
 * Az áramkörben elõfordulható értéket reprezentál.
 */
public enum Value {

    TRUE, FALSE;

    /**
     * String reprezentációját adja vissza
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

    public Value invert() {
        if (this == Value.TRUE) {
            return Value.FALSE;
        } else if (this == Value.FALSE) {
            return Value.TRUE;
        }

        return null;
    }
}
