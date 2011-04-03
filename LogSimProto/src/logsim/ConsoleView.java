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
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Gabor
 */
public class ConsoleView implements Viewable {

    Circuit modell;
    Controllable controller;

    public ConsoleView(Controllable c) {
        this.controller = c;
    }
    /**
     * Bemenet, innen olvassuk a felhasználó választásait
     */
    private static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Kimeneti adatfolyam, ide írunk.
     */
    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, "CP852"), true);
        } catch (Exception e) {
            System.out.println("Outstream error!");
            System.exit(-1);
        }
    }

    /*
     * Felhasználó parancsait olvassa
     */
    @Override
    public void Run() {
        while (true) {
            try {
                String str = keyboard.readLine();

                if (str.equals("exit")) {
                    return;
                } else {
                    controller.Eval(str);
                }
            } catch (IOException ex) {
                out.println("Valami I/O hiba van!");
                return;
            }
        }
    }
    /*
     * Egy komponens bemeneteit és kimeneteit írja ki
     */

    @Override
    public void writeDetails(AbstractComponent ac) {
        out.println(ac.getName());
        out.print(" in: ");
        for (int i = 1; i <= ac.getInputsCount(); i++) {
            out.print(ac.getInputWire(i).getValue());
            if(i!=ac.getInputsCount())
                out.print(", ");
        }
        out.println();
        out.print(" out: ");
        for (int i = 1; i <= ac.getOutputsCount(); i++) {
            out.println(ac.getOutputWire(i).getValue());
            if(i!=ac.getOutputsCount())
                out.print(", ");
        }
        out.println();
    }

    @Override
    public void writeToggle(Toggle t) {
        out.println(t.getName() + ": " + t.getValues()[0]);

    }
}
