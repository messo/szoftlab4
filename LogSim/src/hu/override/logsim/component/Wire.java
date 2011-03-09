package hu.override.logsim.component;

import hu.override.logsim.Value;

/**
 *
 * @author Gabor
 */
public class Wire {

    private Value value = Value.FALSE;
//    private boolean isInputConnected;
//    private boolean isOutputConnected;

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

//    public void setInputConnected(boolean value){
//        isInputConnected = value;
//    }
//
//    public void setOutputConnected(boolean value){
//        isOutputConnected = value;
//    }
}
