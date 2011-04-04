/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import logsim.model.Circuit;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.SourceComponent;
import logsim.model.component.impl.Led;
import logsim.model.component.impl.Scope;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.SevenSegmentDisplay;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Gabor
 */
public class ConsoleView implements Viewable {

    /**
     * Áramkör
     */
    private Circuit modell;
    /**
     * Kontroller
     */
    private Controllable controller;
    /**
     * Bemenet, innen olvassuk a felhasználó választásait
     */
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Kimeneti adatfolyam, ide írunk.
     */
    private static PrintWriter out;

    public ConsoleView(Controllable c) {
        this.controller = c;
    }

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, "CP852"), true);
        } catch (Exception e) {
            System.out.println("Outstream error!");
            System.exit(-1);
        }
    }

    /**
     * Felhasználó parancsait olvassa
     */
    @Override
    public void run() {
        while (true) {
            try {
                String str = keyboard.readLine();

                if (str.equals("exit")) {
                    return;
                } else {
                    controller.eval(str);
                }
            } catch (IOException ex) {
                out.println("Valami I/O hiba van!");
                return;
            }
        }
    }

    /**
     * Egy komponens bemeneteit és kimeneteit írja ki
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
     * Egy scope komponens kiírása
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

    @Override
    public void writeLoadSuccessful() {
        out.println("load successful");
    }

    @Override
    public void writeSaveSuccessful() {
        out.println("save successful");
    }

    @Override
    public void writeLoadFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeSaveFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeSequenceGenerator(SequenceGenerator sg) {
    }

    @Override
    public void writeSimulationSuccessful() {
        out.println("simulation successful");
    }

    @Override
    public void writeSimulationFailed() {
        out.println("simulation failed");
    }

    @Override
    public void writeToggleValue(Toggle sc) {
        out.printf("%s: %s", sc.getName(), (sc.getValues()[0] == Value.TRUE) ? "1" : "0");
        out.println();
    }

    @Override
    public void writeSequenceGeneratorValue(SequenceGenerator sg) {
        out.printf("%s: %s", sg.getName(), sg.getOutputWire(1).getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

    @Override
    public void writeSequenceGeneratorSequence(SequenceGenerator sg) {
        out.printf("%s: ", sg.getName());
        StringBuilder bits = new StringBuilder();
        for (Value v : sg.getValues()) {
            bits.append(v == Value.TRUE ? '1' : '0');
        }
        out.println(bits);
    }

    @Override
    public void writeLedValue(Led led) {
        out.printf("%s: %s", led.getName(), led.getValue() == Value.TRUE ? "1" : "0");
        out.println();
    }

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

    @Override
    public void writeScopeValues(Scope scope) {
        out.printf("%s: ", scope.getName());
        StringBuilder bits = new StringBuilder();
        for (Value v : scope.getValues()) {
            bits.append(v == Value.TRUE ? '1' : '0');
        }
        out.println(bits);
    }
}
