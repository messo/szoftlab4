package logsim.model;

import logsim.model.component.Composite;

/**
 * Áramkört reprezentáló osztály, igazából egy kompozit. Felelõssége megegyzik a kompozitéval.
 */
public class Circuit extends Composite {

    public Circuit() {
        super("Circuit", "circuit", 0, 0);
    }
}
