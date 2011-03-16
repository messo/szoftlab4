package logsim.log;

/**
 * Ezt az interf�szt megval�s�t� objektumokat tudjuk logolni a skeletonban.
 *
 * @author Balint
 */
public interface Loggable {

    /**
     * Visszaadja a p�ld�ny nev�t
     * @return P�ld�ny neve
     */
    String getName();

    /**
     * Visszaadja az oszt�ly nev�t
     * @return Oszt�ly neve
     */
    String getClassName();
}
