package logsim.model;

import logsim.log.Loggable;

/**
 * Az �ramk�rben el�fordulhat� �rt�ket reprezent�l.
 */
public enum Value implements Loggable {

    TRUE, FALSE;

    /**
     * String reprezent�ci�j�t adja vissza
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
