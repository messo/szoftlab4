package logsim.model;

import logsim.model.component.Composite;

/**
 * �ramk�rt reprezent�l� oszt�ly, igaz�b�l egy kompozit. Felel�ss�ge megegyzik a kompozit�val.
 */
public class Circuit extends Composite {

    public Circuit() {
        super("Circuit", "circuit", 0, 0);
    }
}
