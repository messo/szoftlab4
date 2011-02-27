package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 * Komponens interfész, ebbõl származik az IsDisplay és az IsSource interfész.
 * Legalapvetõbb dolgokat írja le (minden komponensnek van neve és értékei).
 *
 * @author balint
 */
public interface Component {

    /**
     * Név beállítása.
     *
     * @param name
     */
    void setName(String name);

    /**
     * Név lekérdezése.
     *
     * @return
     */
    String getName();

    /**
     * Értéke lekérdezése a 0. kimeneten.
     *
     * @return
     */
    Value getValue();

    /**
     * Érték lekérdezése az adott kimeneten.
     *
     * @param idx
     * @return
     */
    Value getValue(int idx);
}
