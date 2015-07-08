package alexiil.utils.logger;

import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;

/** Use Log4J2 instead, its much better :) */
@Deprecated
public class Logger {
    private static Logger mainLog = new Logger().sendToFile();
    public final String name;
    private Logger parent;
    private HashMap<String, Integer> children = new HashMap<String, Integer>();

    public Logger(Logger parent, String logName) {
        this.parent = parent;
        if (parent.children.containsKey(logName)) {
            name = parent.name + "." + logName + "(" + parent.children.get(logName) + ")";
            int i = parent.children.remove(logName);
            parent.children.put(logName, i + 1);
        }
        else {
            name = parent.name + "." + logName;
            parent.children.put(logName, 0);
        }
    }

    public Logger(String name) {
        this(mainLog, name);
    }

    private Logger() {
        name = "alexiil";
    }

    public Logger sendToFile() {
        if (parent != null) {
            parent.sendToFile();
            return this;
        }
        String path = System.getProperty("user.dir") + "\\logs\\";
        path += getDate().replace(" ", "_").replace(":", ".");
        path = path.substring(0, path.length() - 9) + ".txt";
        return this;
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime().toString();
    }

    public String getDateAndTime() {
        return Calendar.getInstance().getTime().toString();
    }

    public void fine(String toLog) {
        log(toLog, Level.FINE);
    }

    public void warn(String toLog) {
        log(toLog, Level.WARNING);
    }

    public void info(String toLog) {
        log(toLog, Level.INFO);
    }

    public void infof(String toLog, Object... args) {
        logf(Level.INFO, toLog, args);
    }

    public void severe(String toLog) {
        log(toLog, Level.SEVERE);
    }

    public void severef(String toLog, Object... args) {
        logf(Level.SEVERE, toLog, args);
    }

    public void log(String toLog, Level level) {
        log(toLog, level, name);
    }

    public void logf(Level level, String toLog, Object... args) {
        log(String.format(toLog, args), level);
    }

    private void log(String toLog, Level level, String names) {
        if (parent != null) { // TODO: Test this!
            parent.log(toLog, level, names);
            return;
        }
        String total = "[" + getDateAndTime() + "][" + names + "][" + level + "][" + toLog + "]";
        System.out.println(total);
    }
}
