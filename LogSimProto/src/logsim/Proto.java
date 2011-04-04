package logsim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

public class Proto implements Controllable {

    Viewable view;
    Simulation s;
    Circuit c;
    Config config;

    public Proto() {
        // tegyük fel, hogy a felhasználó tol egy loadCircuit()-ot.
//        Circuit c = new Parser().parse(new File("test.txt"));
//        c.evaluate();
//        Led led1 = (Led) c.getComponentByName("led1");
//        Led led2 = (Led) c.getComponentByName("led2");
//        System.out.println("led1: " + led1.getValue());
//        System.out.println("led2: " + led2.getValue());
        s = new Simulation();
        try {
            view = new View(this, new FileWriter("output.txt"));
            view.run(new BufferedReader(new FileReader("input.txt")));
        } catch (IOException ex) {
            Logger.getLogger(Proto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //view = new View(this, new OutputStreamWriter(System.out, "CP852"));
        //view.run(new BufferedReader(new InputStreamReader(System.in)));
    }

    public static void main(String[] args) {
        new Proto();
    }

    /* 
     * Parancs értelmezése
     */
    @Override
    public void eval(String s) {
        String cmds[] = s.split(" ");
        if (cmds[0].equals("loadCircuit")) {
            c = new Parser().parse(new File(cmds[1]));
            this.s.setCircuit(c);
            config = new Config(c);
            view.writeLoadSuccessful();
            view.newline();
        } else if (cmds[0].equals("loadSettings")) {
            config.load(new File(cmds[1]));
            view.writeLoadSuccessful();
            view.newline();
        } else if (cmds[0].equals("saveSetting")) {
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
                //összes elem kilistázása
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
