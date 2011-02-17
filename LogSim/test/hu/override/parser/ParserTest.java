package hu.override.parser;

import hu.override.Circuit;
import hu.override.component.AndGate;
import hu.override.component.Component;
import hu.override.component.Inverter;
import hu.override.component.Led;
import hu.override.component.OrGate;
import hu.override.component.SequenceGenerator;
import hu.override.component.Toggle;
import java.util.HashMap;
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
        Circuit circuit = parser.parse("x=AND(0,1)", "y = OR()", "z = LED(0)",
                "t=Toggle()", "u=SeqGen()", "v=NOT(0)");
        HashMap<String, Component> componentMap = circuit.getComponentMap();
        // minden elem szerepel
        Assert.assertNotNull(componentMap.get("x"));
        Assert.assertNotNull(componentMap.get("y"));
        Assert.assertNotNull(componentMap.get("z"));
        Assert.assertNotNull(componentMap.get("t"));
        Assert.assertNotNull(componentMap.get("u"));
        Assert.assertNotNull(componentMap.get("v"));
        // a megfelelõ példányok létrejöttek
        Assert.assertTrue(componentMap.get("x") instanceof AndGate);
        Assert.assertTrue(componentMap.get("y") instanceof OrGate);
        Assert.assertTrue(componentMap.get("z") instanceof Led);
        Assert.assertTrue(componentMap.get("t") instanceof Toggle);
        Assert.assertTrue(componentMap.get("u") instanceof SequenceGenerator);
        Assert.assertTrue(componentMap.get("v") instanceof Inverter);
    }
}
