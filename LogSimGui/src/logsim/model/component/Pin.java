/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim.model.component;

/**
 *
 * @author Balint
 */
public class Pin {

    public enum Type {

        INPUT, OUTPUT;
    }
    private AbstractComponent ac;
    private int pin;
    private Type type;

    public Pin(AbstractComponent ac, int pin, Type type) {
        this.ac = ac;
        this.pin = pin;
        this.type = type;
    }

    public AbstractComponent getComponent() {
        return ac;
    }

    public int getPin() {
        return pin;
    }
}
