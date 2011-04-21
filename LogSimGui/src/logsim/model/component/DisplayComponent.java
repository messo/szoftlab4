package logsim.model.component;

/**
 * Megjelen�t� t�pus� komponenst reprezent�l. T�le sz�rmaznak a megjelen�t�k (pl. led).
 */
public abstract class DisplayComponent extends AbstractComponent {

    /**
     * Konstruktor. Nem lesz kimenete.
     * 
     * @param name Komponens neve
     * @param inputCount Bemenetek sz�ma
     */
    protected DisplayComponent(String name, int inputCount) {
        // kimenet n�lk�li komponens
        super(name, inputCount, 0);
    }

    /**
     * Hozz�ad�s kompozithoz
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }
}
