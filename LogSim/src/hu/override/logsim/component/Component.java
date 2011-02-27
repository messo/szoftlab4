package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 * Komponens interf�sz, ebb�l sz�rmazik az IsDisplay �s az IsSource interf�sz.
 * Legalapvet�bb dolgokat �rja le (minden komponensnek van neve �s �rt�kei).
 *
 * @author balint
 */
public interface Component {

    /**
     * N�v be�ll�t�sa.
     *
     * @param name
     */
    void setName(String name);

    /**
     * N�v lek�rdez�se.
     *
     * @return
     */
    String getName();

    /**
     * �rt�ke lek�rdez�se a 0. kimeneten.
     *
     * @return
     */
    Value getValue();

    /**
     * �rt�k lek�rdez�se az adott kimeneten.
     *
     * @param idx
     * @return
     */
    Value getValue(int idx);
}
