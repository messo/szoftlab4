package logsim.model.component.impl;

import java.util.LinkedList;
import java.util.Queue;
import logsim.Viewable;
import logsim.model.Value;
import logsim.model.component.Composite;

/**
 * Egy oszcilloszk�pot reprezent�l.
 */
public class Scope extends Led {

    private Queue<Value> memory;
    private int size;

    /**
     * Konstruktor. 1 bemenet� megjelen�t�
     * @param name Led neve
     */
    public Scope(int size, String name) {
        super(name);
        this.size = size;
        memory = new LinkedList<Value>();
    }

    /**
     * Visszaadja az eddig elt�rolt �rt�keket
     * @return
     */
    public Value[] getValues() {
        return memory.toArray(new Value[memory.size()]);
    }

    /**
     * Elt�roljuk az �rt�ket a mem�ri�ban
     */
    public void commit() {
        if(memory.size() == size) {
            memory.remove();
        }

        memory.add(getInput(1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEvaluation() {
        // nop.
    }

    @Override
    public Scope copy(String name) {
        return new Scope(size, name);
    }

    /**
     * Komponens ki�r�sa a viewra.
     * @param view
     */
    @Override
    public void writeTo(Viewable view) {
        view.writeScopeDetails(this);
    }

    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }

    @Override
    public void writeValueTo(Viewable view) {
        view.writeScopeValues(this);
    }
}
