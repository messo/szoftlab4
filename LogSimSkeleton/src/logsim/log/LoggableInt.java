package logsim.log;

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
