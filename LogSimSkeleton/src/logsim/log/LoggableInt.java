/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim.log;

/**
 *
 * @author Balint
 */
public class LoggableInt implements Loggable {

    private int intValue;

    public LoggableInt(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String getName() {
        return String.valueOf(intValue);
    }

    @Override
    public String getClassName() {
        return "int";
    }
}
