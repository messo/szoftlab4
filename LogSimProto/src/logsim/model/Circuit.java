package logsim.model;

import logsim.model.component.Composite;

/**
 * Áramkört reprezentáló osztály, igazából egy kompozit.
 */
public class Circuit extends Composite {

    public Circuit() {
        super("Circuit", "circuit", 0, 0);
    }

    /**
     * Áramkör inicializálása
     */
    public void init() {
        // Tesztesetekben implementáljuk.
    }
}
