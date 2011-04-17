/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;

/**
 * Egy konkrét kimeneti implementáció, mely OutputStreamWriter-be ír ki,
 * így a konzolos megjelenítés és fájlba írás megoldott.
 */
public class View implements Viewable {

    /**
     * Kontroller
     */
    private Controller controller;
    /**
     * Kimeneti adatfolyam, ide írunk.
     */
    private PrintWriter out;

    /**
     * Létehozzuk a Viewt egy kontrollerrel és a kimenettel, ide fog menni a kimenet.
     *
     * @param c kontroller
     * @param out kimenet
     */
    public View(Controller c, OutputStreamWriter out) {
        this.controller = c;
        try {
            this.out = new PrintWriter(out, true);
        } catch (Exception e) {
            System.out.println("Outstream error!");
            System.exit(-1);
        }
    }

    /**
     * Kiírunk egy komponenst (be és kimenetek)
     * @param ac komponens
     */
    @Override
    public void writeDetails(AbstractComponent ac) {
        out.println(ac.getName() + ":");
        out.print(" in: ");
        for (int i = 1; i <= ac.getInputsCount(); i++) {
            out.print(ac.getInputWire(i).getValue() == Value.TRUE ? '1' : '0');
            if (i != ac.getInputsCount()) {
                out.print(", ");
            }
        }
        out.println();
        out.print(" out: ");
        for (int i = 1; i <= ac.getOutputsCount(); i++) {
            out.print(ac.getOutputWire(i).getValue() == Value.TRUE ? '1' : '0');
            if (i != ac.getOutputsCount()) {
                out.print(", ");
            }
        }
        out.println();
    }

    /**
     * Kiírunk egy scope-ot
     * @param scope oszcilloszkóp
     */
    @Override
    public void writeScopeDetails(Scope ac) {
        out.println(ac.getName() + ":");
        out.print(" in: ");
        for (int i = 1; i <= ac.getInputsCount(); i++) {
            out.print(ac.getInputWire(i).getValue() == Value.TRUE ? '1' : '0');
            if (i != ac.getInputsCount()) {
                out.print(", ");
            }
        }
        out.println();
        out.print(" out: ");
        Value[] values = ac.getValues();
        for (int i = 0; i < values.length; i++) {
            if (i != 0) {
                out.print(", ");
            }
            out.print(values[i] == Value.TRUE ? '1' : '0');
        }
        out.println();
    }

    /**
     * Kiírjuk, hogy a betöltés sikeres
     */
    @Override
    public void writeLoadSuccessful() {
        out.println("load successful");
    }

    /**
     * Kiírjuk, hogy a config fájl mentés sikeres
     */
    @Override
    public void writeSaveSuccessful() {
        out.println("save successful");
    }

    /**
     * Kiírjuk, hogy a betöltés sikertelen
     */
    @Override
    public void writeLoadFailed() {
        //throw new UnsupportedOperationException("Not supported yet.");
        out.println("load failed");
    }

    /**
     * Kiírjuk, hogy a config fájl mentése sikertelen
     */
    @Override
    public void writeSaveFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Szekvenciagenerátor szekvenciájának kiírása
     * @param sg szekvenciagenerátor
     */
    @Override
    public void writeSequenceGenerator(SequenceGenerator sg) {
        out.print(sg.getName());
        out.print(": ");
    }

    /**
     * Kiírjuk, hogy a szimuláció sikeres
     */
    @Override
    public void writeSimulationSuccessful() {
        out.println("simulation successful");
    }

    /**
     * Kiírjuk, hogy a szimuláció sikertelen
     */
    @Override
    public void writeSimulationFailed() {
        out.println("simulation failed");
    }

    /**
     * Kiírja a kapcsoló állapotát
     * @param toggle kapcsoló
     */
    @Override
    public void writeToggleValue(Toggle sc) {
        out.printf("%s: %s", sc.getName(), (sc.getValues()[0] == Value.TRUE) ? "1" : "0");
        out.println();
    }

    /**
     * Kiírja a jelgenerátor éppen kiadott értékét
     * @param sg szekvenciagenerátor
     */
    @Override
    public void writeSequenceGeneratorValue(SequenceGenerator sg) {
        out.printf("%s: %s", sg.getName(), sg.getOutputWire(1).getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

    /**
     * Kiírja a jelgenerátor szekvenciáját
     * @param sg szekvenciagenerátor
     */
    @Override
    public void writeSequenceGeneratorSequence(SequenceGenerator sg) {
        out.printf("%s: ", sg.getName());
        StringBuilder bits = new StringBuilder();
        for (Value v : sg.getValues()) {
            bits.append(v == Value.TRUE ? '1' : '0');
        }
        out.println(bits);
    }

    /**
     * Kiírja a led értékét
     * @param led led
     */
    @Override
    public void writeLedValue(Led led) {
        out.printf("%s: %s", led.getName(), led.getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

    /**
     * Kiírja a 7-szegmentes kijelzõ szegmenseit.
     * @param seg 7-szegmenses kijelzõ
     */
    @Override
    public void writeSevenSegmentDisplayValues(SevenSegmentDisplay seg) {
        out.printf("%s: ", seg.getName());
        StringBuilder bits = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            if (bits.length() != 0) {
                bits.append(", ");
            }
            bits.append(seg.getSegment(i) == Value.TRUE ? '1' : '0');
        }
        out.println(bits);
    }

    /**
     * Kiírja a scope által tárolt értékeket
     * @param scope oszcilloszkóp
     */
    @Override
    public void writeScopeValues(Scope scope) {
        out.printf("%s: ", scope.getName());
        StringBuilder bits = new StringBuilder();
        for (Value v : scope.getValues()) {
            bits.append(v == Value.TRUE ? '1' : '0');
        }
        out.println(bits);
    }

    /**
     * Új sor a kimeneten
     */
    @Override
    public void newline() {
        out.println();
    }
}
