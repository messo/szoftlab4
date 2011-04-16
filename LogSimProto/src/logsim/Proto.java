package logsim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import logsim.model.Circuit;
import logsim.model.Simulation;
import logsim.model.Value;
import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.SequenceGenerator;
import logsim.model.component.impl.Toggle;

/**
 * Prototípus vezérlõ osztálya.
 *
 */
public class Proto implements Controller {

    /**
     * Megjelenítõ
     */
    private Viewable view;
    /**
     * Szimuláció
     */
    private Simulation s;
    /**
     * Áramkör
     */
    private Circuit c;
    /**
     * Konfiguráció menedzselése
     */
    private Config config;

    public Proto(String[] args) {
        s = new Simulation();
        try {
            InputStreamReader input = null;
            OutputStreamWriter output = null;
            if (args.length == 3) {
                input = new FileReader(args[1]);
                output = new FileWriter(args[2]);
            } else if (args.length == 2) {
                // TODO - ha 1 paraméter, akkor vagy onnan olvasunk, vagy oda írunk.
            } else {
                input = new InputStreamReader(System.in);
                output = new OutputStreamWriter(System.out, "CP852");
            }

            view = new View(this, output);
            run(new BufferedReader(input));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Program belépési pontja.
     * 
     * @param args paraméterek
     */
    public static void main(String[] args) {
        new Proto(args);
    }

    /**
     * Felhasználó parancsait olvassa
     */
    @Override
    public final void run(BufferedReader input) {
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
     * Parancs értelmezése
     *
     * @param s parancs
     */
    private void eval(String s) {
        String cmds[] = s.split(" ");
        if (cmds[0].equals("loadCircuit")) {
            c = new Parser().parse(new File(cmds[1]));
            if (c == null) {
                view.writeLoadFailed();
            } else {
                this.s.setCircuit(c);
                config = new Config(c);
                view.writeLoadSuccessful();
            }
            view.newline();
        } else if (cmds[0].equals("loadSettings")) {
            if (config.load(new File(cmds[1])) == 0) {
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
