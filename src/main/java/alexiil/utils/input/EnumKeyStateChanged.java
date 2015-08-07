package alexiil.utils.input;

import java.awt.event.KeyEvent;

public enum EnumKeyStateChanged {
    PRESSED,
    RELEASED,
    TYPED;

    public static final EnumKeyStateChanged[] MINOR_CHANGE = { PRESSED, RELEASED };
    public static final EnumKeyStateChanged[] ALL = values();

    public static EnumKeyStateChanged valueOf(KeyEvent event) {
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
