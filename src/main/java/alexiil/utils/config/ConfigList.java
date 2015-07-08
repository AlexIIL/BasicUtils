package alexiil.utils.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigList {
    private static Map<String, Map<String, Option>> options = Collections.synchronizedMap(new HashMap<String, Map<String, Option>>());
    private static List<Option> optionList = Collections.synchronizedList(new ArrayList<Option>());
    private static List<Option> optionDisplay = Collections.synchronizedList(new ArrayList<Option>());
    
    public static void addOption(String name, Option option) {
        if (name == null || option == null)
            throw new Error("The option or its name was null");
        if (optionList.contains(option))
            return;// throw new Error("The option \"" + name + "\" already exists!");
        if (!options.containsKey(option.getType()))
            options.put(option.getType(), Collections.synchronizedMap(new HashMap<String, Option>()));
        if (!options.get(option.getType()).containsKey(name)) {
            options.get(option.getType()).put(name, option);
            optionList.add(option);
            if (option.isShown())
                optionDisplay.add(option);
        }
        else
            return;// throw new Error("The option \"" + name + "\" already exists!");
    }
    
    public static List<Option> getOptionDisplayList() {
        return optionDisplay;
    }
    
    public static List<Option> getOptionList() {
        return optionList;
    }
}
