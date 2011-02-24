package hu.override.logsim.exception;

/**
 *
 * @author balint
 */
public class CircuitAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>CircuitAlreadyExistsException</code> without detail message.
     */
    public CircuitAlreadyExistsException() {
    }


    /**
     * Constructs an instance of <code>CircuitAlreadyExistsException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CircuitAlreadyExistsException(String msg) {
        super(msg);
    }
}
