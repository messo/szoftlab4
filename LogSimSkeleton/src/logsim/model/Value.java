package logsim.model;

import logsim.log.Loggable;

/**
 * Az �ramk�rben el�fordulhat� �rt�ket reprezent�l.
 *
 */
public enum Value implements Loggable {

    TRUE, FALSE;

    /**
     * A saj�t �rt�k�t sz�vegk�nt adja vissza
     * @return �rt�k
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
     * Invert�lja az adott �rt�ket. Ennek addig van �rtelme, am�g 2 f�le
     * �llapot fordulhat el� a rendszerben.
     *
     * @return Invert�lt �rt�k
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
