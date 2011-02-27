package hu.override.logsim;

/**
 * Jelgener�tor-l�ptet� sz�l. Feladata, hogy az �ramk�rt utas�tsa a jelgener�torok l�ptes�s�re
 * a felhaszn�l� �ltal konfigur�lhat� id�k�z�nk�nt.
 *
 * @author balint
 */
public class SequenceGeneratorStepper extends Thread {

    /**
     * A szimul�ci�, aki sz�m�ra l�pteti a jelgener�torokat
     */
    private Simulation simulation;
    /**
     * A fut�si ciklus v�ltoz�ja; ha ez hamis lesz, akkor le�ll a sz�l
     */
    private boolean shouldRun;
    /**
     * Sz�net k�t l�ptet�s k�z�tt -- felhaszn�l� �ltal konfigur�lhat�
     */
    private long pause = 50;

    public SequenceGeneratorStepper(Simulation simulation) {
        super("SequenceGeneratorStepper");
        this.simulation = simulation;
    }

    /**
     * Megadott id�k�z�nk�nt az �ramk�r�n megh�vjuk a stepGenerators() met�dust.
     */
    @Override
    public void run() {
        shouldRun = true;
        while (shouldRun) {
            // el�sz�r v�runk, mert most minden jelgener�tor kiadja az els� bitet
            // teh�t ennek is hagyni kell kicsit futni
            try {
                sleep(pause);
            } catch (InterruptedException ex) {
            }

            // az�rt ind�tunk �j sz�lat, hogy biztosan pause-nyi id�nk�nt
            // t�rt�njen l�ptet�s.
            new Thread() {

                @Override
                public void run() {
                    simulation.getCircuit().stepGenerators();
                }
            }.start();
        }
    }

    /**
     * K�t l�ptet�s k�z�tt eltelt id� �ll�t�sa (l�ptet�si sebess�g)
     *
     * @param pause sz�net milliszekundumban
     */
    public void setPause(long pause) {
        this.pause = pause;
    }

    /**
     * Le�ll a l�ptet�s �s befejez�dik a sz�l fut�sa.
     */
    void stopStepper() {
        shouldRun = false;
    }
}
