package alexiil.utils.input;

import java.awt.event.MouseEvent;

public enum EnumMouseEvent {
    CLICKED,
    PRESSED,
    RELEASED,
    DRAGGED,
    MOVED;

    public static final EnumMouseEvent[] MINOR_CHANGE = { PRESSED, RELEASED };

    public static EnumMouseEvent valueOf(MouseEvent event) {
        int id = event.getID();
        if (id == MouseEvent.MOUSE_CLICKED) {
            return CLICKED;
        } else if (id == MouseEvent.MOUSE_PRESSED) {
            return PRESSED;
        } else if (id == MouseEvent.MOUSE_RELEASED) {
            return RELEASED;
        } else if (id == MouseEvent.MOUSE_DRAGGED) {
            return DRAGGED;
        } else if (id == MouseEvent.MOUSE_MOVED) {
            return MOVED;
        }
        return null;
    }
}
