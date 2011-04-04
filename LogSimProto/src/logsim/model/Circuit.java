package logsim.model;

import logsim.model.component.Composite;

/**
 * �ramk�rt reprezent�l� oszt�ly, igaz�b�l egy kompozit.
 */
public class Circuit extends Composite {

    public Circuit() {
        super("Circuit", "circuit", 0, 0);
    }

    /**
     * �ramk�r inicializ�l�sa
     */
    public void init() {
        // Tesztesetekben implement�ljuk.
    }
}
