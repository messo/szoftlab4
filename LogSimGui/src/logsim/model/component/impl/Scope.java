package logsim.model.component.impl;

import java.util.LinkedList;
import java.util.Queue;
import logsim.ComponentViewCreator;
import logsim.model.Value;
import logsim.model.component.Composite;
import logsim.view.component.ComponentView;

/**
 * Egy oszcilloszk�pot reprezent�l. Elt�rolt �rt�kek egy sorba ker�lnek bele, mely fix m�ret�.
 */
public class Scope extends Led {

    /**
     * Elt�rolt �rt�kek sora.
     */
    private Queue<Value> memory;
    /**
     * Elt�rolhat� �rt�kek sz�ma.
     */
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
        if (memory.size() == size) {
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
     * Hozz�ad�s kompozithoz.
     * @param composite
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }

    @Override
    public ComponentView createView(ComponentViewCreator cvc) {
        return cvc.createView(this);
    }
}
