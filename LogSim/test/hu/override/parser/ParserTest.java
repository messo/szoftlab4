package hu.override.parser;

import hu.override.Circuit;
import hu.override.component.AndGate;
import hu.override.component.Inverter;
import hu.override.component.Led;
import hu.override.component.OrGate;
import hu.override.component.SequenceGenerator;
import hu.override.component.Toggle;
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
