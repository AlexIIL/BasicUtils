package alexiil.utils.config;

import java.util.HashMap;

/** @deprecated Use Json now */
public class ConfigPart {
    private HashMap<String, ConfigPart> parts = new HashMap<String, ConfigPart>();
    private HashMap<String, String> stringParts = new HashMap<String, String>();

    public final ConfigPart parent;

    public ConfigPart(ConfigPart parent) {
        this.parent = parent;
    }

    public void addPart(String name, ConfigPart part) {
        parts.put(name, part);
    }

    public void addPart(String name, String saved) {
        stringParts.put(name, saved);
    }

    public HashMap<String, ConfigPart> getParts() {
        return parts;
    }

    public HashMap<String, String> getStringParts() {
        return stringParts;
    }
}
