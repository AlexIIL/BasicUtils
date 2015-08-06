package alexiil.utils.input;

import java.awt.event.KeyEvent;

public enum EnumPressableMotion {
    PRESSED,
    RELEASED,
    TYPED;

    public static final EnumPressableMotion[] MINOR_CHANGE = { PRESSED, RELEASED };
    public static final EnumPressableMotion[] ALL = values();

    public static EnumPressableMotion valueOf(KeyEvent event) {
        int id = event.getID();
        if (id == KeyEvent.KEY_PRESSED) {
            return PRESSED;
        } else if (id == KeyEvent.KEY_RELEASED) {
            return RELEASED;
        } else if (id == KeyEvent.KEY_TYPED) {
            return TYPED;
        }
        return null;
    }
}
