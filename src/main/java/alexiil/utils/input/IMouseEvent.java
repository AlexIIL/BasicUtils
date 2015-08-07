package alexiil.utils.input;

public interface IMouseEvent {
    /** This is where the mouse is right now, at the time the event fired */
    int getX();

    /** This is where the mouse is right now, at the time the event fired */
    int getY();

    /** This is where the mouse started (So for PRESSED it will be the same as {@link #getX()}, for RELEASED it will be
     * wherever the PRESSED event was {@link #getX()}, for CLICKED it will probably be the same, and for MOVED and
     * DRAPPED it will be the first PRESSED event was. */
    int getXStart();

    /** This is where the mouse started (So for PRESSED it will be the same as {@link #getY()}, for RELEASED it will be
     * wherever the PRESSED event was {@link #getY()}, for CLICKED it will probably be the same, and for MOVED and
     * DRAPPED it will be the first PRESSED event was. */
    int getYStart();

    EnumMouseEvent getType();

    EnumKeyModifier[] getModifiers();
}
