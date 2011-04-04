package logsim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logsim.model.Circuit;
import logsim.model.component.SourceComponent;
import logsim.model.Value;

/**
 * Konfigurációs fájlok kezelése, azok írása az áramkör alapján, illetve azok betöltése
 * az áramkörbe.
 */
public class Config {

    /**
     * Regex kifejezés az illesztéshez (beolvasásnál)
     */
    private static Pattern sourceComponentPattern = Pattern.compile("\\s*(.*?)\\s*=\\s*(.+?)");
    /**
     * Áramkör, aminek mentjük a dolgait
     */
    private Circuit circuit;

    /**
     * Példány létrehozása az áramkörhöz.
     * 
     * @param circuit
     */
    public Config(Circuit circuit) {
        this.circuit = circuit;
    }

    /**
     * Elmenti a kapcsolók illetve jelgenerátorok aktuális állapotát egy fájlba
     *
     * @param file fájl, amibe elmentõdik a konfiguráció
     *
     * @return sikeresség/hibakód
     */
    public int save(File file) {
        List<SourceComponent> sources = circuit.getSourceComponents();
        Value[] values;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (SourceComponent source : sources) {
                bw.write(source.getName());
                bw.write('=');
                int tmp = source.getValues().length;
                values = new Value[tmp];
                values = source.getValues();
                for (int i = 0; i < tmp; i++) {
                    if (values[i] == Value.TRUE) {
                        bw.write('1');
                    } else {
                        bw.write('0');
                    }
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return 0;
    }

    /**
     * Betölti egy fájlból a kapcsolók illetve jelgenerátorok konfigurációját
     *
     * @param file fájl, ahonnan beolvassa az értékeket
     *
     * @return sikeresség/hibakód
     */
    public int load(File file) {
        String line;
        SourceComponent component;
        Value[] values;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                Matcher matcher = sourceComponentPattern.matcher(line);
                if (matcher.matches()) {
                    component = (SourceComponent) circuit.getComponentByName(matcher.group(1));
                    String valuesString = matcher.group(2);
                    values = new Value[valuesString.length()];
                    for (int i = 0; i < valuesString.length(); i++) {
                        if (valuesString.charAt(i) == '0') {
                            values[i] = Value.FALSE;
                        } else {
                            values[i] = Value.TRUE;
                        }
                    }
                    component.setValues(values);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        return 0;
    }
}
