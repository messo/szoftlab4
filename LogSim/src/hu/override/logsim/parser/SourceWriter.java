package hu.override.logsim.parser;

import hu.override.logsim.component.IsSource;

/**
 * Kiírja egy fájlba a jelforrások értékeit
 *
 * @author Balint
 */
public class SourceWriter {

    /**
     * A megadott nevû fájlba fog történni az írás
     *
     * @param fileName
     */
    public SourceWriter(String fileName) {
        // létrehozzuk a fájlt
    }

    /**
     * Hozzáadjuk a fájlhoz az adott jelforrás beállítását
     *
     * @param source
     */
    public void add(IsSource source) {
        // beírjuk a fájlba.
    }

    /**
     * Bezárjuk a fájlt.
     */
    public void close() {
    }
}
