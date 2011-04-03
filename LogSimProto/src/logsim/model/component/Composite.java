package logsim.model.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logsim.log.Logger;
import logsim.model.component.impl.Gnd;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.Vcc;

/**
 *
 */
public class Composite extends AbstractComponent {

    /**
     * Regex minta egy komponens bemeneteinek a feldolgoz�s�hoz
     */
    private static Pattern inputPattern = Pattern.compile("(.*?)(?:\\[([0-9]+)\\])?");
    private static final int cycleLimit = 100;
    /**
     * Komponensek list�ja
     */
    private Map<String, AbstractComponent> components;
    /**
     * Jelforr�s t�pus� komponensek list�ja (pl. kapcsol�)
     */
    private List<SourceComponent> sources;
    /**
     * Megjelen�t� t�pus� komponensek list�ja (pl. led)
     */
    private List<DisplayComponent> displays;
    /**
     * Kompozit t�pus� komponensek list�ja
     */
    private List<Composite> composites;
    private Node[] inputNodes;
    private Node[] outputNodes;
    private String type;

    public Composite(String type, String name, int inputCount, int outputCount) {
        super(name, inputCount, outputCount);

        this.type = type;

        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        components = new HashMap<String, AbstractComponent>();
        composites = new ArrayList<Composite>();

        inputNodes = new Node[inputCount];
        for (int i = 0; i < inputCount; i++) {
            // FIXME!
            inputNodes[i] = new Node(10, "node" + (i + 1));
        }

        outputNodes = new Node[outputCount];
        for (int i = 0; i < outputCount; i++) {
            // FIXME!
            outputNodes[i] = new Node(10, "outputNode" + (i + 1));
        }
    }

    @Override
    public void setInput(int inputPin, Wire wire) {
        super.setInput(inputPin, wire);
        inputNodes[inputPin - 1].setInput(1, wire);
    }

    @Override
    public void setOutput(int outputPin, Wire wire) {
        super.setOutput(outputPin, wire);
        outputNodes[outputPin - 1].setOutput(1, wire);
    }

    /**
     * �ltal�nos t�pus� komponens hozz�ad�sa
     * @param c Hozz�adand� komponens
     */
    public void add(AbstractComponent c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("�ltal�nos komponens hozz�ad�sa a(z) kompozithoz/�ramk�rh�z");
        components.put(c.getName(), c);
        Logger.logReturn();
    }

    /**
     * Jelforr�s t�pus� komponens hozz�ad�sa
     * @param sc Hozz�adand� komponens
     */
    public void add(SourceComponent sc) {
        Logger.logCall(this, "add", sc);
        Logger.logComment("Jelforr�s hozz�ad�sa a(z) kompozithoz/�ramk�rh�z");
        components.put(sc.getName(), sc);
        sources.add(sc);
        Logger.logReturn();
    }

    /**
     * Kijelz� t�pus� komponens hozz�ad�sa
     * @param dc Hozz�adand� komponens
     */
    public void add(DisplayComponent dc) {
        Logger.logCall(this, "add", dc);
        Logger.logComment("Kijelz� hozz�ad�sa a(z) kompozithoz/�ramk�rh�z");
        components.put(dc.getName(), dc);
        displays.add(dc);
        Logger.logReturn();
    }

    /**
     * Kompozit t�pus� komponens hozz�ad�sa
     * @param c
     */
    public void add(Composite c) {
        Logger.logCall(this, "add", c);
        Logger.logComment("Kompozit hozz�ad�sa a(z) kompozithoz/�ramk�rh�z");
        components.put(c.getName(), c);
        composites.add(c);
        Logger.logReturn();
    }

    @Override
    protected void onEvaluation() {
        int counter = 0;
        eval:
        while (counter < cycleLimit) {
            for (Node n : inputNodes) {
                n.evaluate();
            }
            for (AbstractComponent c : components.values()) {
                c.evaluate();
            }
            for (Node n : outputNodes) {
                n.evaluate();
            }

            for (AbstractComponent c : components.values()) {
                if (c.isChanged()) {
                    Logger.logComment("M�g egy ciklus...");
                    counter++;
                    continue eval;
                }
            }
            break;
        }
        if (counter == cycleLimit) {
            throw new RuntimeException("Nincs stacion�rius �llapot!");
        }
    }

    /**
     * Jelgener�torok l�ptet�se
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
     * A flipflopok jelenlegi kimenet�t elmentj�k bels� �llapotnak, �s az �rajel
     * bemenet�n l�v� �rt�ket pedig elt�roljuk az �ldetekt�l�s �rdek�ben.
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
        return "Composite[" + type + "]";
    }

    @Override
    public void addTo(Composite composite) {
        Logger.logCall(this, "addTo", composite);
        composite.add(this);
        Logger.logReturn();
    }

    @Override
    public Composite copy(String variableName) {
        Composite c = new Composite(type, variableName, inputNodes.length, outputNodes.length);

        Map<Wire, Wire> map = new HashMap<Wire, Wire>();

        // bels�t is m�soljuk le!
        // 1. v�gig megy�nk az inputnodeokon �s minden wire-re l�trehozunk egy m�sikat
        for (int nodeIdx = 0; nodeIdx < inputNodes.length; nodeIdx++) {
            for (int i = 1; i <= inputNodes[nodeIdx].getOutputsCount(); i++) {
                Wire old = inputNodes[nodeIdx].getOutputWire(i);
                if (old != null) {
                    Wire newW = new Wire(old.getName());
                    map.put(old, newW);
                    c.inputNodes[nodeIdx].setOutput(i, newW);
                }
            }
        }

        // 2. m�soljunk le minden komponenst.
        for (AbstractComponent ac : components.values()) {
            // lem�soljuk, majd hozz�adjuk.
            AbstractComponent newC = ac.copy(ac.getName());
            newC.addTo(c);
            // bemenetek
            for (int i = 1; i <= ac.getInputsCount(); i++) {
                Wire old = ac.getInputWire(i);
                if (map.get(old) == null) {
                    map.put(old, new Wire(old.getName()));
                }
                Wire newW = map.get(old);
                newC.setInput(i, newW);
            }
            // kimenetek
            for (int i = 1; i <= ac.getOutputsCount(); i++) {
                Wire old = ac.getOutputWire(i);
                if (map.get(old) == null) {
                    map.put(old, new Wire(old.getName()));
                }
                Wire newW = map.get(old);
                newC.setOutput(i, newW);
            }
        }

        // 3. v�gig megy�nk az outputnodeokon �s minden wiret bek�t�nk
        for (int nodeIdx = 0; nodeIdx < outputNodes.length; nodeIdx++) {
            Wire w = map.get(outputNodes[nodeIdx].getInputWire(1));
            c.outputNodes[nodeIdx].setInput(1, w);
        }

        return c;
    }

    public void connectComponents(Map<AbstractComponent, String[]> connections,
            String[] inputs, String[] outputs) {

        int vccIdx = 0;
        int gndIdx = 0;

        // rendezz�k a keres�s miatt!
        if (inputs != null) {
            Arrays.sort(inputs);
        }

        // itt t�roljuk, hogy melyik inputnode �ppen melyik kimenet�n�l j�runk
        int[] inputNodesOutputIdx = new int[inputNodes.length];
        for (int i = 0; i < inputNodesOutputIdx.length; i++) {
            inputNodesOutputIdx[i] = 1;
        }

        for (AbstractComponent target : connections.keySet()) {
            int idx = 1;
            for (String argument : connections.get(target)) {
                int compositeInputIdx = -1;
                if (inputs != null) {
                    compositeInputIdx = Arrays.binarySearch(inputs, argument);
                }

                // bek�t�s
                Wire wire = new Wire("wire_to_" + target.getName());
                target.setInput(idx++, wire);

                AbstractComponent source;

                if (argument.equals("0")) {
                    source = new Gnd("gnd_" + (gndIdx++));
                    source.addTo(this);
                    source.setOutput(1, wire);
                } else if (argument.equals("1")) {
                    source = new Vcc("vcc_" + (vccIdx++));
                    source.addTo(this);
                    source.setOutput(1, wire);
                } else if (compositeInputIdx >= 0) {
                    // a param�ter egy input param�ter, teh�t az adott komponenst
                    // a node egy kimenet�re kell k�tni
                    inputNodes[compositeInputIdx].setOutput(inputNodesOutputIdx[compositeInputIdx]++, wire);
                } else {
                    Matcher paramMatcher = inputPattern.matcher(argument);
                    if (paramMatcher.matches()) {
                        source = components.get(paramMatcher.group(1));
                        if (source == null) {
                            throw new RuntimeException("Nem l�tez� komponens!");
                        } else {
                            int outputPin = paramMatcher.group(2) != null ? Integer.parseInt(paramMatcher.group(2)) : 1;
                            source.setOutput(outputPin, wire);
                        }
                    }
                }
            }
        }

        // megfelel� elemek kimeneteit r�k�tni a komponens kimeneteire.
        AbstractComponent source;
        if (outputs != null) {
            int idx = 0;
            for (String output : outputs) {
                Wire wire = new Wire("wire_to_output" + idx);
                Matcher paramMatcher = inputPattern.matcher(output);
                if (paramMatcher.matches()) {
                    source = components.get(paramMatcher.group(1));
                    if (source == null) {
                        throw new RuntimeException("Nem l�tez� komponens!");
                    } else {
                        int outputPin = paramMatcher.group(2) != null ? Integer.parseInt(paramMatcher.group(2)) : 1;
                        source.setOutput(outputPin, wire);
                    }
                }

                outputNodes[idx++].setInput(1, wire);
            }
        }
    }

    public AbstractComponent getComponentByName(String name) {
        int sepIdx = name.indexOf('.');
        if (sepIdx == -1) {
            return components.get(name);
        } else {
            String compositeName = name.substring(0, sepIdx);
            for (Composite c : composites) {
                if (c.getName().equals(compositeName)) {
                    return c.getComponentByName(name.substring(sepIdx + 1));
                }
            }
        }

        return null;
    }
}
