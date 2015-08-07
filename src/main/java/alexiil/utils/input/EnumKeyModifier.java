package alexiil.utils.input;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

public enum EnumKeyModifier {
    ALT,
    ALT_GRAPH,
    CONTROL,
    META,
    SHIFT;

    public static final EnumKeyModifier[] NONE = {};

    public static EnumKeyModifier[] valueOf(InputEvent event) {
        List<EnumKeyModifier> modifiers = new ArrayList<>();
        if (event.isAltDown()) {
            modifiers.add(ALT);
        } else if (event.isAltGraphDown()) {
            modifiers.add(ALT_GRAPH);
        } else if (event.isControlDown()) {
            modifiers.add(CONTROL);
        } else if (event.isMetaDown()) {
            modifiers.add(META);
        } else if (event.isShiftDown()) {
            modifiers.add(SHIFT);
        }
        return modifiers.toArray(new EnumKeyModifier[modifiers.size()]);
    }

    public boolean isInArray(EnumKeyModifier[] array) {
        for (EnumKeyModifier mod : array) {
            if (mod == this) {
                return true;
            }
        }
        return false;
    }
}
