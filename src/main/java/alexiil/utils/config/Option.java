package alexiil.utils.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import alexiil.utils.render.Colour;

public class Option implements IConfigurable {
    public final String text, name;
    private Object option;
    private boolean isEnum = false;
    public final List<Object> enums = Collections.synchronizedList(new ArrayList<Object>());
    private final List<Runnable> listners = Collections.synchronizedList(new ArrayList<Runnable>());
    
    public Option(String name, String text, Object def) {
        ConfigList.addOption(name, this);
        this.name = name;
        this.text = text;
        option = def;
    }
    
    @SuppressWarnings("unchecked") public Option setEnum(Object... options) {
        isEnum = true;
        enums.clear();
        for (Object st : options) {
            if (st instanceof List<?>)
                for (Object obj : ((List<Object>) st))
                    enums.add(obj);
            else
                enums.add(st);
        }
        return this;
    }
    
    public Option addListner(Runnable list) {
        if (!listners.contains(list))
            listners.add(list);
        return this;
    }
    
    public int getAsInt() {
        if (option instanceof Integer)
            return (Integer) option;
        if (option instanceof String)
            return Integer.valueOf((String) option);
        return 0;
    }
    
    public boolean getAsBoolean() {
        if (option instanceof Boolean)
            return (Boolean) option;
        return false;
    }
    
    public Colour getAsColour() {
        if (option instanceof Colour)
            return (Colour) option;
        return Colour.GREEN;
    }
    
    public boolean storesEnum() {
        return isEnum;
    }
    
    public Object getStored() {
        return option;
    }
    
    public void setValue(Object obj) {
        option = obj;
        for (Runnable run : listners)
            run.run();
    }
    
    public int getCurrentIndex() {
        if (!isEnum)
            return 0;
        if (enums.contains(option))
            return enums.indexOf(option);
        return 0;
    }
    
    public String getType() {
        return "options";
    }
    
    public boolean isShown() {
        return true;
    }
    
    @Override public void read(ConfigPart cp) {
        // Set<String> keys = cp.getParts().keySet();
        // for (String s : keys)
        // {
        // switch (s)
        // {
        // case ("Boolean"):
        // ;
        // }
        // }
    }
    
    @Override public ConfigPart write(ConfigPart cp) {
        if (getStored() instanceof Boolean)
            cp.addPart("Boolean", "" + getStored());
        else if (getStored() instanceof Integer)
            cp.addPart("Integer", "" + getStored());
        else if (getStored() instanceof Double)
            cp.addPart("Double", "" + getStored());
        else if (getStored() instanceof Float)
            cp.addPart("Float", "" + getStored());
        else if (getStored() instanceof Long)
            cp.addPart("Long", "" + getStored());
        else if (getStored() instanceof Byte)
            cp.addPart("Byte", "" + getStored());
        else if (getStored() instanceof Colour)
            cp.addPart("Colour", "" + getStored());
        else if (storesEnum()) {}
        return cp;
    }
}