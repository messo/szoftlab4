package logsim.model.component;

import logsim.log.Loggable;
import logsim.log.Logger;
import logsim.model.Value;

/**
 *
 * @author Gabor
 */
public class Wire implements Loggable {

    private String name;
    
    public Wire(String name) {
        this.name = name;
        Logger.logCreate(this);
        Logger.logReturn();
    }

    public void setValue(Value value) {
        Logger.logCall(this, "setValue", value);
        Logger.logReturn();
    }

    public Value getValue() {
        Logger.logCall(this, "getValue");
        Value v = Logger.logAskValue(this, "vezetéken lévõ érték");
        Logger.logReturn(v.toString());
        return v;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getClassName() {
        return "Wire";
    }
}
