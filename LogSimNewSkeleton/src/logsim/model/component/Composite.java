package logsim.model.component;

import java.util.ArrayList;
import java.util.List;
import logsim.log.Logger;
import logsim.model.component.impl.Node;

/**
 *
 */
public class Composite extends AbstractComponent {

    private static final int cycleLimit = 2;
    /**
     * Komponensek listája
     */
    private List<AbstractComponent> components;
    /**
     * Jelforrás típusú komponensek listája (pl. kapcsoló)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelenítõ típusú komponensek listája (pl. led)
     */
    private List<DisplayComponent> displays;
    /**
     * Kompozit típusú komponensek listája
     */
    private List<Composite> composites;
    private Node[] inputNodes;
    private Node[] outputNodes;

    public Composite(String name, int inputCount, int outputCount, int[] nodesSize) {
        super(name, inputCount, outputCount);

        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        components = new ArrayList<AbstractComponent>();
        composites = new ArrayList<Composite>();

        inputNodes = new Node[inputCount];
        for (int i = 0; i < inputCount; i++) {
            inputNodes[i] = new Node(nodesSize[i], "node" + i);
        }

        outputNodes = new Node[outputCount];
        for (int i = 0; i < outputCount; i++) {
            outputNodes[i] = new Node(1, "outputNode" + i);
        }
    }

    @Override
    public void setInput(int inputPin, Wire wire) {
        inputNodes[inputPin].setInput(0, wire);
    }

    @Override
    public void setOutput(int outputPin, Wire wire) {
        outputNodes[outputPin].setOutput(0, wire);
    }

    /**
     * Visszaadja az adott bemenetre kötött
     *
     * @param inputPin
     * @return
     */
    public Node getInputNode(int inputPin) {
        return inputNodes[inputPin];
    }

    public Node getOutputNode(int outputPin) {
        return outputNodes[outputPin];
    }

    /**
     * Általános típusú komponens hozzáadása
     * @param c Hozzáadandó komponens
     */
    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("Általános komponens hozzáadása a(z) kompozithoz/áramkörhöz");
        components.add(c);
        Logger.logReturn();
    }

    /**
     * Jelforrás típusú komponens hozzáadása
     * @param sc Hozzáadandó komponens
     */
    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforrás hozzáadása a(z) kompozithoz/áramkörhöz");
        components.add(sc);
        sources.add(sc);
        Logger.logReturn();
    }

    /**
     * Kijelzõ típusú komponens hozzáadása
     * @param dc Hozzáadandó komponens
     */
    public void add(DisplayComponent dc) {
        Logger.logCall(this, "add", dc);
        Logger.logComment("Kijelzõ hozzáadása a(z) kompozithoz/áramkörhöz");
        components.add(dc);
        displays.add(dc);
        Logger.logReturn();
    }

    /**
     * Kompozit típusú komponens hozzáadása
     * @param c
     */
    public void add(Composite c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("Kompozit hozzáadása a(z) kompozithoz/áramkörhöz");
        components.add(c);
        composites.add(c);
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        int counter = 0;
        while (counter < cycleLimit) {
            for (Node n : inputNodes) {
                n.evaluate();
            }
            for (AbstractComponent c : components) {
                c.evaluate();
            }
            for (Node n : outputNodes) {
                n.evaluate();
            }

            if (!isChanged()) {
                break;
            }
            counter++;
        }
        if (counter == cycleLimit) {
            throw new RuntimeException("Nincs stacionárius állapot!");
        }
    }

    /**
     * Megvizsgálja, hogy a kompozitban történt-e változás
     *
     * @return változott-e
     */
    @Override
    public boolean isChanged() {
        boolean ret;
        Logger.logCall(this, "isChanged");
        ret = false;
        for (AbstractComponent c : components) {
            if (c.isChanged()) {
                ret = true;
                break;
            }
        }

        Logger.logReturn(String.valueOf(ret));
        return ret;
    }

    /**
     * Jelgenerátorok léptetése
     */
    public void stepGenerators() {
        Logger.logCall(this, "stepGenerators");
        for (Composite c : composites) {
            c.stepGenerators();
        }
        // TODO - for generators
        Logger.logReturn();
    }

    /**
     * A flipflopok jelenlegi kimenetét elmentjük belsõ állapotnak, és az órajel
     * bemenetén lévõ értéket pedig eltároljuk az éldetektálás érdekében.
     */
    public void commitFlipFlops() {
        Logger.logCall(this, "commitFlipFlops");
        for (Composite c : composites) {
            c.commitFlipFlops();
        }
        // TODO - for flipflops
        Logger.logReturn();
    }

    @Override
    public String getClassName() {
        return "Composite";
    }

    @Override
    public void addTo(Composite composite) {
        Logger.logCall(this, "addTo", composite);
        composite.add(this);
        Logger.logReturn();
    }
}
