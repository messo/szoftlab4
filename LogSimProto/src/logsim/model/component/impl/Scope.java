package logsim.model.component.impl;

import java.util.LinkedList;
import java.util.Queue;
import logsim.Viewable;
import logsim.model.Value;
import logsim.model.component.Composite;

/**
 * Egy oszcilloszkópot reprezentál. Eltárolt értékek egy sorba kerülnek bele, mely fix méretû.
 */
public class Scope extends Led {

    /**
     * Eltárolt értékek sora.
     */
    private Queue<Value> memory;
    /**
     * Eltárolható értékek száma.
     */
    private int size;

    /**
     * Konstruktor. 1 bemenetû megjelenítõ
     * @param name Led neve
     */
    public Scope(int size, String name) {
        super(name);
        this.size = size;
        memory = new LinkedList<Value>();
    }

    /**
     * Visszaadja az eddig eltárolt értékeket
     * @return
     */
    public Value[] getValues() {
        return memory.toArray(new Value[memory.size()]);
    }

    /**
     * Eltároljuk az értéket a memóriában
     */
    public void commit() {
        if(memory.size() == size) {
            memory.remove();
        }

        memory.add(getInput(1));
    }

    @Override
    protected void onEvaluation() {
        // nop.
    }

    @Override
    public Scope copy(String name) {
        return new Scope(size, name);
    }

    /**
     * Komponens kiírása a viewra.
     * @param view
     */
    @Override
    public void writeTo(Viewable view) {
        view.writeScopeDetails(this);
    }

    /**
     * Hozzáadás kompozithoz.
     * @param composite
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }

    /**
     * Érték kiírása a kimenetre.
     * @param view
     */
    @Override
    public void writeValueTo(Viewable view) {
        view.writeScopeValues(this);
    }
}
