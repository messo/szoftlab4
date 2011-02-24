package hu.override.parser;

import hu.override.logsim.parser.Parser;
import hu.override.logsim.Circuit;
import hu.override.logsim.component.impl.AndGate;
import hu.override.logsim.component.impl.Inverter;
import hu.override.logsim.component.impl.Led;
import hu.override.logsim.component.impl.OrGate;
import hu.override.logsim.component.impl.SequenceGenerator;
import hu.override.logsim.component.impl.Toggle;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author balint
 */
public class ParserTest {

    private Parser parser;

    public ParserTest() {
        parser = new Parser();
    }

    @Test
    public void componentTest() {
        try {
            Circuit circuit = parser.parse("x=AND(0,1)", "y = OR()", "z = LED(0)",
                    "t=Toggle()", "u=SeqGen()", "v=NOT(0)");
            // minden elem szerepel
            Assert.assertNotNull(circuit.getComponentByName("x"));
            Assert.assertNotNull(circuit.getComponentByName("y"));
            Assert.assertNotNull(circuit.getComponentByName("z"));
            Assert.assertNotNull(circuit.getComponentByName("t"));
            Assert.assertNotNull(circuit.getComponentByName("u"));
            Assert.assertNotNull(circuit.getComponentByName("v"));
            // a megfelelõ példányok létrejöttek
            Assert.assertTrue(circuit.getComponentByName("x") instanceof AndGate);
            Assert.assertTrue(circuit.getComponentByName("y") instanceof OrGate);
            Assert.assertTrue(circuit.getComponentByName("z") instanceof Led);
            Assert.assertTrue(circuit.getComponentByName("t") instanceof Toggle);
            Assert.assertTrue(circuit.getComponentByName("u") instanceof SequenceGenerator);
            Assert.assertTrue(circuit.getComponentByName("v") instanceof Inverter);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            Assert.assertTrue(false);
        }
    }

    @Test
    public void inputTest() {
        // howto?
    }
}
