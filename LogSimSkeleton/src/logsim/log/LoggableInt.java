package logsim.log;

/**
 * Egy becsomagoló osztály, mely segítségével az int típusú objektumok is loggolhatóvá
 * válnak, ez fõleg a {@link Logger#logCall(logsim.log.Loggable, java.lang.String, logsim.log.Loggable[])}
 * metódus hívásánál hasznos.
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
