package hu.override.logsim.exception;

/**
 *
 * @author balint
 */
public class InvalidCircuitDefinitionException extends Exception {

    /**
     * Creates a new instance of <code>InvalidCircuitDefinitionException</code> without detail message.
     */
    public InvalidCircuitDefinitionException() {
    }


    /**
     * Constructs an instance of <code>InvalidCircuitDefinitionException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidCircuitDefinitionException(String msg) {
        super(msg);
    }
}
