package logsim.model;

import logsim.model.component.Composite;

/**
 * Áramkört reprezentáló osztály, igazából egy kompozit.
 */
public class Circuit extends Composite {

    public Circuit() {
        super("circuit", 0, 0, null);
    }

    /**
     * Áramkör inicializálása
     */
    public void init() {
        // Tesztesetekben implementáljuk.
    }

    @Override
    public String getClassName() {
        return "Circuit";
    }
}
