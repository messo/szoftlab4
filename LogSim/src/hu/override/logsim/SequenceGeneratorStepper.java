package hu.override.logsim;

/**
 * Jelgenerátor-léptetõ szál. Feladata, hogy az áramkört utasítsa a jelgenerátorok léptesésére
 * a felhasználó által konfigurálható idõközönként.
 *
 * @author balint
 */
public class SequenceGeneratorStepper extends Thread {

    /**
     * A szimuláció, aki számára lépteti a jelgenerátorokat
     */
    private Simulation simulation;
    /**
     * A futási ciklus változója; ha ez hamis lesz, akkor leáll a szál
     */
    private boolean shouldRun;
    /**
     * Szünet két léptetés között -- felhasználó által konfigurálható
     */
    private long pause = 50;

    public SequenceGeneratorStepper(Simulation simulation) {
        super("SequenceGeneratorStepper");
        this.simulation = simulation;
    }

    /**
     * Megadott idõközönként az áramkörön meghívjuk a stepGenerators() metódust.
     */
    @Override
    public void run() {
        shouldRun = true;
        while (shouldRun) {
            // elõször várunk, mert most minden jelgenerátor kiadja az elsõ bitet
            // tehát ennek is hagyni kell kicsit futni
            try {
                sleep(pause);
            } catch (InterruptedException ex) {
            }

            // azért indítunk új szálat, hogy biztosan pause-nyi idõnként
            // történjen léptetés.
            new Thread() {

                @Override
                public void run() {
                    simulation.getCircuit().stepGenerators();
                }
            }.start();
        }
    }

    /**
     * Két léptetés között eltelt idõ állítása (léptetési sebesség)
     *
     * @param pause szünet milliszekundumban
     */
    public void setPause(long pause) {
        this.pause = pause;
    }

    /**
     * Leáll a léptetés és befejezõdik a szál futása.
     */
    void stopStepper() {
        shouldRun = false;
    }
}
