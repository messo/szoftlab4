package logsim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

/**
 * Protot�pus vez�rl� oszt�lya.
 *
 */
public class Proto implements Controller {

    /**
     * Megjelen�t�
     */
    private Viewable view;
    /**
     * Szimul�ci�
     */
    private Simulation s;
    /**
     * �ramk�r
     */
    private Circuit c;
    /**
     * Konfigur�ci� menedzsel�se
     */
    private Config config;

    public Proto() {
        // tegy�k fel, hogy a felhaszn�l� tol egy loadCircuit()-ot.
//        Circuit c = new Parser().parse(new File("test.txt"));
//        c.evaluate();
//        Led led1 = (Led) c.getComponentByName("led1");
//        Led led2 = (Led) c.getComponentByName("led2");
//        System.out.println("led1: " + led1.getValue());
//        System.out.println("led2: " + led2.getValue());
        s = new Simulation();
        try {
            view = new View(this, new FileWriter("output.txt"));
            run(new BufferedReader(new FileReader("input.txt")));
        } catch (IOException ex) {
            Logger.getLogger(Proto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //view = new View(this, new OutputStreamWriter(System.out, "CP852"));
        //run(new BufferedReader(new InputStreamReader(System.in)));
    }

    /**
     * Program bel�p�si pontja.
     * 
     * @param args param�terek
     */
    public static void main(String[] args) {
        new Proto();
    }

    /**
     * Felhaszn�l� parancsait olvassa
     */
    @Override
    public void run(BufferedReader input) {
        while (true) {
            try {
                String str = input.readLine();
                if (str == null) {
                    break;
                }
                if (str.equals("exit")) {
                    return;
                } else {
                    eval(str);
                }
            } catch (IOException ex) {
                System.out.println("Valami I/O hiba van!");
                return;
            }
        }
    }

    /**
     * Parancs �rtelmez�se
     *
     * @param s parancs
     */
    private void eval(String s) {
        String cmds[] = s.split(" ");
        if (cmds[0].equals("loadCircuit")) {
            c = new Parser().parse(new File(cmds[1]));
            this.s.setCircuit(c);
            config = new Config(c);
            view.writeLoadSuccessful();
            view.newline();
        } else if (cmds[0].equals("loadSettings")) {
            if (config.load(new File(cmds[1])) == 0){;
                view.writeLoadSuccessful();
            } else {
                view.writeLoadFailed();
            }
            view.newline();
        } else if (cmds[0].equals("saveSettings")) {
            config.save(new File(cmds[1]));
            view.writeSaveSuccessful();
            view.newline();
        } else if (cmds[0].equals("switch")) {
            Toggle sw = (Toggle) c.getComponentByName(cmds[1]);
            Value[] v = sw.getValues();
            v[0] = v[0].invert();
            sw.setValues(v);
            sw.writeValueTo(view);
            view.newline();
        } else if (cmds[0].equals("setSeqGen")) {
            SequenceGenerator sg = (SequenceGenerator) c.getComponentByName(cmds[1]);
            Value[] values = new Value[cmds[2].length()];
            for (int i = 0; i < cmds[2].length(); i++) {
                if (cmds[2].charAt(i) == '1') {
                    values[i] = Value.TRUE;
                } else if (cmds[2].charAt(i) == '0') {
                    values[i] = Value.FALSE;
                } else {
                    values[i] = null;
                }
            }
            sg.setValues(values);
            view.writeSequenceGeneratorSequence(sg);
            view.newline();
        } else if (cmds[0].equals("check")) {
            if (cmds[1].equals("-all")) {
                //�sszes elem kilist�z�sa
                for (AbstractComponent ac : c.getComponents()) {
                    ac.writeTo(view);
                }
            } else {
                view.writeDetails(c.getComponentByName(cmds[1]));
            }
            view.newline();
        } else if (cmds[0].equals("step")) {
            boolean success = this.s.start();
            if (success) {
                view.writeSimulationSuccessful();
                for (AbstractComponent ac : c.getSourceComponents()) {
                    ac.writeValueTo(view);
                }
                for (AbstractComponent ac : c.getDisplayComponents()) {
                    ac.writeValueTo(view);
                }
            } else {
                view.writeSimulationFailed();
            }
            view.newline();
        }
    }
}
