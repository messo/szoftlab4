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

/**
 *
 * @author Gabor
 */
public class ConsoleView implements Viewable {
    Circuit modell;
    Controllable controller;

    public ConsoleView(Controllable c)
    {
        this.controller=c;
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
    public void Run()
    {
        while(true){
            try {
                    String str = keyboard.readLine();

                    if (str.equals("exit"))
                    {
                        return;
                    } else
                    {
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
    public void WriteDetails(AbstractComponent ac) {
        out.println("Komponens: " + ac.getName());
        for(int i=0; i<ac.getInputsWire().length;i++){
            out.println(String.format("Bemenet[%d]: ", ac.getInputsWire()[i].getValue()));
        }
        for(int i=0;i<ac.getOutputsWire().length;i++){
            out.println(String.format("Kimenet[%d]: ", ac.getOutputsWire()[i].getValue()));
        }
    }
}
