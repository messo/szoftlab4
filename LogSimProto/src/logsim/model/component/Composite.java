package logsim.model.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logsim.model.component.impl.Gnd;
import logsim.model.component.impl.Node;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Vcc;

/**
 * Kompozit elem leírása, kiértékelésnél a tartalmazott komponenseket kiértékeli, lépteti
 * a jelgenerátorokat stb. Ha nem áll be stacionárius állapotba a kiértékelésnél, akkor ezt jelzi kifelé.
 */
public class Composite extends AbstractComponent {

    /**
     * Regex minta egy komponens bemeneteinek a feldolgozásához
     */
    private static Pattern inputPattern = Pattern.compile("(.*?)(?:\\[([0-9]+)\\])?");
    /**
     * Max. ciklusok száma
     */
    private static final int cycleLimit = 100;
    /**
     * Komponensek listája
     */
    private Map<String, AbstractComponent> components;
    /**
     * Jelforrás típusú komponensek listája (pl. kapcsoló)
     */
    private List<SourceComponent> sources;
    /**
     * Jelgenerátorok listája
     */
    private List<SequenceGenerator> generators;
    /**
     * Flipflopok listája
     */
    private List<FlipFlop> flipFlops;
    /**
     * Megjelenítõ típusú komponensek listája (pl. led)
     */
    private List<DisplayComponent> displays;
    /**
     * Kompozit típusú komponensek listája
     */
    private List<Composite> composites;
    /**
     * Oszcillátor típusú komponensek listája
     */
    private List<Scope> scopes;
    /**
     * Bemeneti csomópontok
     */
    private Node[] inputNodes;
    /**
     * Kimeneti csomópontok
     */
    private Node[] outputNodes;
    /**
     * Kompozit típusa
     */
    private String type;

    /**
     * Adott típusú és nevû komponens létrehozása a megfelelõ lábszámmal.
     *
     * @param type
     * @param name
     * @param inputCount
     * @param outputCount
     */
    public Composite(String type, String name, int inputCount, int outputCount) {
        super(name, inputCount, outputCount);

        this.type = type;

        sources = new ArrayList<SourceComponent>();
        displays = new ArrayList<DisplayComponent>();
        components = new HashMap<String, AbstractComponent>();
        generators = new ArrayList<SequenceGenerator>();
        flipFlops = new ArrayList<FlipFlop>();
        composites = new ArrayList<Composite>();
        scopes = new ArrayList<Scope>();

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

    /**
     * Bemenet beállítása
     * @param inputPin
     * @param wire
     */
    @Override
    public void setInput(int inputPin, Wire wire) {
        super.setInput(inputPin, wire);
        inputNodes[inputPin - 1].setInput(1, wire);
    }

    /**
     * Kimenet beállítása
     * @param inputPin
     * @param wire
     */
    @Override
    public void setOutput(int outputPin, Wire wire) {
        super.setOutput(outputPin, wire);
        outputNodes[outputPin - 1].setOutput(1, wire);
    }

    /**
     * Általános típusú komponens hozzáadása
     * @param c Hozzáadandó komponens
     */
    public void add(AbstractComponent c) {
        components.put(c.getName(), c);
    }

    /**
     * Jelforrás típusú komponens hozzáadása
     * @param sc Hozzáadandó komponens
     */
    public void add(SourceComponent sc) {
        components.put(sc.getName(), sc);
        sources.add(sc);
    }

    /**
     * Jelgenerátor komponens hozzáadása
     * @param sg Hozzáadandó komponens
     */
    public void add(SequenceGenerator sg) {
        components.put(sg.getName(), sg);
        sources.add(sg);
        generators.add(sg);
    }

    /**
     * Flipflop komponens hozzáadása
     * @param ff Hozzáadandó komponens
     */
    public void add(FlipFlop ff) {
        components.put(ff.getName(), ff);
        flipFlops.add(ff);
    }

    /**
     * Kijelzõ típusú komponens hozzáadása
     * @param dc Hozzáadandó komponens
     */
    public void add(DisplayComponent dc) {
        components.put(dc.getName(), dc);
        displays.add(dc);
    }

    /**
     * Oszcillátor típusú komponens hozzáadása
     * @param scope Hozzáadandó komponens
     */
    public void add(Scope scope) {
        components.put(scope.getName(), scope);
        displays.add(scope);
        scopes.add(scope);
    }

    /**
     * Kompozit típusú komponens hozzáadása
     * @param c
     */
    public void add(Composite c) {
        components.put(c.getName(), c);
        composites.add(c);
    }

    /**
     * Kiértékelési ciklus
     */
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
                    counter++;
                    continue eval;
                }
            }
            break;
        }
        if (counter == cycleLimit) {
            throw new RuntimeException("Nincs stacionárius állapot!");
        }
        commitScopes();
        stepGenerators();
        commitFlipFlops();
    }

    /**
     * Jelgenerátorok léptetése
     */
    private void stepGenerators() {
        for (Composite c : composites) {
            c.stepGenerators();
        }
        for (SequenceGenerator sg : generators) {
            sg.step();
        }
    }

    /**
     * A flipflopok jelenlegi kimenetét elmentjük belsõ állapotnak, és az órajel
     * bemenetén lévõ értéket pedig eltároljuk az éldetektálás érdekében.
     */
    private void commitFlipFlops() {
        for (Composite c : composites) {
            c.commitFlipFlops();
        }
        for (FlipFlop ff : flipFlops) {
            ff.commit();
        }
    }

    /**
     * Oszcilloszkópok véglegesítése
     */
    private void commitScopes() {
        for (Composite c : composites) {
            c.commitScopes();
        }
        for (Scope scope : scopes) {
            scope.commit();
        }
    }

    /**
     * Kompozit hozzáadása kompozithoz.
     * @param composite
     */
    @Override
    public void addTo(Composite composite) {
        composite.add(this);
    }

    /**
     * Kompozit lemásolása (példányosításnál használjuk.)
     * @param variableName
     * @return
     */
    @Override
    public Composite copy(String variableName) {
        Composite c = new Composite(type, variableName, inputNodes.length, outputNodes.length);

        Map<Wire, Wire> map = new HashMap<Wire, Wire>();

        // belsõt is másoljuk le!
        // 1. végig megyünk az inputnodeokon és minden wire-re létrehozunk egy másikat
        for (int nodeIdx = 0; nodeIdx < inputNodes.length; nodeIdx++) {
            for (int i = 1; i <= inputNodes[nodeIdx].getOutputsCount(); i++) {
                Wire old = inputNodes[nodeIdx].getOutputWire(i);
                if (old != null) {
                    Wire newW = new Wire();
                    map.put(old, newW);
                    c.inputNodes[nodeIdx].setOutput(i, newW);
                }
            }
        }

        // 2. másoljunk le minden komponenst.
        for (AbstractComponent ac : components.values()) {
            // lemásoljuk, majd hozzáadjuk.
            AbstractComponent newC = ac.copy(ac.getName());
            newC.addTo(c);
            // bemenetek
            for (int i = 1; i <= ac.getInputsCount(); i++) {
                Wire old = ac.getInputWire(i);
                if (map.get(old) == null) {
                    map.put(old, new Wire());
                }
                Wire newW = map.get(old);
                newC.setInput(i, newW);
            }
            // kimenetek
            for (int i = 1; i <= ac.getOutputsCount(); i++) {
                Wire old = ac.getOutputWire(i);
                if (map.get(old) == null) {
                    map.put(old, new Wire());
                }
                Wire newW = map.get(old);
                newC.setOutput(i, newW);
            }
        }

        // 3. végig megyünk az outputnodeokon és minden wiret bekötünk
        for (int nodeIdx = 0; nodeIdx < outputNodes.length; nodeIdx++) {
            Wire w = map.get(outputNodes[nodeIdx].getInputWire(1));
            c.outputNodes[nodeIdx].setInput(1, w);
        }

        return c;
    }

    /**
     * Komponensek összekötése
     * @param connections
     * @param inputs
     * @param outputs
     */
    public void connectComponents(Map<AbstractComponent, String[]> connections,
            String[] inputs, String[] outputs) {

        int vccIdx = 0;
        int gndIdx = 0;

        // rendezzük a keresés miatt!
        if (inputs != null) {
            Arrays.sort(inputs);
        }

        // itt tároljuk, hogy melyik inputnode éppen melyik kimeneténél járunk
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

                // bekötés
                Wire wire = new Wire();
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
                    // a paraméter egy input paraméter, tehát az adott komponenst
                    // a node egy kimenetére kell kötni
                    inputNodes[compositeInputIdx].setOutput(inputNodesOutputIdx[compositeInputIdx]++, wire);
                } else {
                    Matcher paramMatcher = inputPattern.matcher(argument);
                    if (paramMatcher.matches()) {
                        source = components.get(paramMatcher.group(1));
                        if (source == null) {
                            throw new RuntimeException("Nem létezõ komponens!");
                        } else {
                            int outputPin = paramMatcher.group(2) != null ? Integer.parseInt(paramMatcher.group(2)) : 1;
                            source.setOutput(outputPin, wire);
                        }
                    }
                }
            }
        }

        // megfelelõ elemek kimeneteit rákötni a komponens kimeneteire.
        AbstractComponent source;
        if (outputs != null) {
            int idx = 0;
            for (String output : outputs) {
                Wire wire = new Wire();
                Matcher paramMatcher = inputPattern.matcher(output);
                if (paramMatcher.matches()) {
                    source = components.get(paramMatcher.group(1));
                    if (source == null) {
                        throw new RuntimeException("Nem létezõ komponens!");
                    } else {
                        int outputPin = paramMatcher.group(2) != null ? Integer.parseInt(paramMatcher.group(2)) : 1;
                        source.setOutput(outputPin, wire);
                    }
                }

                outputNodes[idx++].setInput(1, wire);
            }
        }
    }

    /**
     * Komponens lekérése a neve alapján (delegálja a kérést, ha kell).
     * @param name
     * @return
     */
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

    /**
     * Jelforrások listája
     * @return
     */
    public List<SourceComponent> getSourceComponents() {
        return sources;
    }

    /**
     * Jelgenerátorok listája
     * @return
     */
    public List<SequenceGenerator> getStepGenerators(){
        return generators;
    }

    /**
     * Megjelenítõk listája
     * @return
     */
    public List<DisplayComponent> getDisplayComponents() {
        return displays;
    }

    /**
     * Összes tartalmazott komponens listája
     * @return
     */
    public Collection<AbstractComponent> getComponents() {
        return components.values();
    }
}
