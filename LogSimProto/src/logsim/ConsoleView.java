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
 * Flehasználó parancsit olvassa
 */
    @Override
    public void Run()
    {
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

    @Override
    public void WriteDetails(AbstractComponent ac) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
