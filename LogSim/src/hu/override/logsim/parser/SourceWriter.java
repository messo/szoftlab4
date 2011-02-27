package hu.override.logsim.parser;

import hu.override.logsim.component.IsSource;

/**
 * Segítségével kiírhatjuk egy fájlba a jelforrások értékeit.
 *
 * @author balint
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
     * Bezárjuk a fájlt, ha végeztünk, ezt meg kell hívnunk.
     */
    public void close() {
        // TODO - implement
    }
}
