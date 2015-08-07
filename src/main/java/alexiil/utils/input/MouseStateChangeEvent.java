package alexiil.utils.input;

import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MouseStateChangeEvent implements IMouseEvent {
    private final int x, y, xStart, yStart;
    private final EnumButton button;
    private final EnumMouseEvent motion;
    private final EnumKeyModifier[] modifiers;

    public MouseStateChangeEvent(int x, int y, int xStart, int yStart, EnumButton button, EnumMouseEvent motion, EnumKeyModifier[] modifiers) {
        this.x = x;
        this.y = y;
        this.xStart = xStart;
        this.yStart = yStart;
        this.button = button;
        this.motion = motion;
        this.modifiers = modifiers;
    }

    public MouseStateChangeEvent(MouseEvent event) {
        this(event.getX(), event.getY(), event.getX(), event.getY(), EnumButton.valueOf(event), EnumMouseEvent.valueOf(event), EnumKeyModifier
            .valueOf(event));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EnumButton getButton() {
        return button;
    }

    @Override
    public EnumMouseEvent getType() {
        return motion;
    }

    @Override
    public int getXStart() {
        return xStart;
    }

    @Override
    public int getYStart() {
        return yStart;
    }

    @Override
    public String toString() {
        return "MouseStateChangeEvent [x=" + x + ", y=" + y + ", xStart=" + xStart + ", yStart=" + yStart + ", button=" + button + ", motion="
            + motion + ", modifiers=" + Arrays.toString(modifiers) + "]";
    }

    @Override
    public EnumKeyModifier[] getModifiers() {
        return modifiers;
    }
}
