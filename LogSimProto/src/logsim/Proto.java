package logsim;

import java.io.File;
import logsim.model.Circuit;
import logsim.model.component.impl.Led;

public class Proto {

    public Proto() {
        // tegyük fel, hogy a felhasználó tol egy loadCircuit()-ot.
        Circuit c = new Parser().parse(new File("test.txt"));
        c.evaluate();
        Led led1 = (Led) c.getComponentByName("led1");
        Led led2 = (Led) c.getComponentByName("led2");
        System.out.println("led1: " + led1.getValue());
        System.out.println("led2: " + led2.getValue());
    }

    public static void main(String[] args) {
        new Proto();
    }
}
