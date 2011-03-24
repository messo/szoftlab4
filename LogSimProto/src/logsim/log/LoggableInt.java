package logsim.log;

/**
 * Egy becsomagol� oszt�ly, mely seg�ts�g�vel az int t�pus� objektumok is loggolhat�v�
 * v�lnak, ez f�leg a {@link Logger#logCall(logsim.log.Loggable, java.lang.String, logsim.log.Loggable[])}
 * met�dus h�v�s�n�l hasznos.
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
