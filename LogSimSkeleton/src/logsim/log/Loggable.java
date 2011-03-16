package logsim.log;

/**
 * Ezt az interfészt megvalósító objektumokat tudjuk logolni a skeletonban.
 *
 * @author Balint
 */
public interface Loggable {

    /**
     * Visszaadja a példány nevét
     * @return Példány neve
     */
    String getName();

    /**
     * Visszaadja az osztály nevét
     * @return Osztály neve
     */
    String getClassName();
}
