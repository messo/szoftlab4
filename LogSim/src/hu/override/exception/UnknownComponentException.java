package hu.override.exception;

/**
 *
 * @author balint
 */
public class UnknownComponentException extends Exception {

    /**
     * Creates a new instance of <code>UnknownComponentException</code> without detail message.
     */
    public UnknownComponentException() {
    }

    /**
     * Constructs an instance of <code>UnknownComponentException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UnknownComponentException(String msg) {
        super(msg);
    }
}
