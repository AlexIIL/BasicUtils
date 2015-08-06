package alexiil.utils.input;

import java.awt.event.KeyEvent;

public class AKeyEvent implements IPressableEvent {
    private final int keyCode;
    private final char keyChar;
    private final EnumPressableMotion motion;
    private final EnumKeyModifier[] modifiers;

    public AKeyEvent(int keyCode, char keyChar, EnumPressableMotion motion, EnumKeyModifier... modifiers) {
        this.keyCode = keyCode;
        this.keyChar = keyChar;
        this.motion = motion;
        this.modifiers = modifiers;
    }

    public AKeyEvent(KeyEvent event) {
        this(event.getKeyCode(), event.getKeyChar(), EnumPressableMotion.valueOf(event), EnumKeyModifier.valueOf(event));
    }

    public int getKey() {
        return keyCode;
    }

    public char getChar() {
        return keyChar;
    }

    public EnumKeyModifier[] getModifiers() {
        return modifiers;
    }

    @Override
    public EnumPressableMotion getMotionType() {
        return motion;
    }
}
