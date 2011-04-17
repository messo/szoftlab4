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
 * Egy konkr�t kimeneti implement�ci�, mely OutputStreamWriter-be �r ki,
 * �gy a konzolos megjelen�t�s �s f�jlba �r�s megoldott.
 */
public class View implements Viewable {

    /**
     * Kontroller
     */
    private Controller controller;
    /**
     * Kimeneti adatfolyam, ide �runk.
     */
    private PrintWriter out;

    /**
     * L�tehozzuk a Viewt egy kontrollerrel �s a kimenettel, ide fog menni a kimenet.
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
     * Ki�runk egy komponenst (be �s kimenetek)
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
     * Ki�runk egy scope-ot
     * @param scope oszcilloszk�p
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
     * Ki�rjuk, hogy a bet�lt�s sikeres
     */
    @Override
    public void writeLoadSuccessful() {
        out.println("load successful");
    }

    /**
     * Ki�rjuk, hogy a config f�jl ment�s sikeres
     */
    @Override
    public void writeSaveSuccessful() {
        out.println("save successful");
    }

    /**
     * Ki�rjuk, hogy a bet�lt�s sikertelen
     */
    @Override
    public void writeLoadFailed() {
        //throw new UnsupportedOperationException("Not supported yet.");
        out.println("load failed");
    }

    /**
     * Ki�rjuk, hogy a config f�jl ment�se sikertelen
     */
    @Override
    public void writeSaveFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Szekvenciagener�tor szekvenci�j�nak ki�r�sa
     * @param sg szekvenciagener�tor
     */
    @Override
    public void writeSequenceGenerator(SequenceGenerator sg) {
        out.print(sg.getName());
        out.print(": ");
    }

    /**
     * Ki�rjuk, hogy a szimul�ci� sikeres
     */
    @Override
    public void writeSimulationSuccessful() {
        out.println("simulation successful");
    }

    /**
     * Ki�rjuk, hogy a szimul�ci� sikertelen
     */
    @Override
    public void writeSimulationFailed() {
        out.println("simulation failed");
    }

    /**
     * Ki�rja a kapcsol� �llapot�t
     * @param toggle kapcsol�
     */
    @Override
    public void writeToggleValue(Toggle sc) {
        out.printf("%s: %s", sc.getName(), (sc.getValues()[0] == Value.TRUE) ? "1" : "0");
        out.println();
    }

    /**
     * Ki�rja a jelgener�tor �ppen kiadott �rt�k�t
     * @param sg szekvenciagener�tor
     */
    @Override
    public void writeSequenceGeneratorValue(SequenceGenerator sg) {
        out.printf("%s: %s", sg.getName(), sg.getOutputWire(1).getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

    /**
     * Ki�rja a jelgener�tor szekvenci�j�t
     * @param sg szekvenciagener�tor
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
     * Ki�rja a led �rt�k�t
     * @param led led
     */
    @Override
    public void writeLedValue(Led led) {
        out.printf("%s: %s", led.getName(), led.getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

    /**
     * Ki�rja a 7-szegmentes kijelz� szegmenseit.
     * @param seg 7-szegmenses kijelz�
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
     * Ki�rja a scope �ltal t�rolt �rt�keket
     * @param scope oszcilloszk�p
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
     * �j sor a kimeneten
     */
    @Override
    public void newline() {
        out.println();
    }
}
