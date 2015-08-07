package alexiil.utils.input;

import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MouseMovedEvent implements IMouseEvent {
    private final int x, y, xStart, yStart;
    private final EnumButton[] buttons;
    private final EnumMouseEvent type;

    public MouseMovedEvent(int x, int y, int xStart, int yStart, EnumButton[] buttons, EnumMouseEvent type) {
        this.x = x;
        this.y = y;
        this.xStart = xStart;
        this.yStart = yStart;
        this.buttons = buttons;
        this.type = type;
    }

    public MouseMovedEvent(MouseContext context, MouseEvent event) {
        this(event.getX(), event.getY(), event.getX(), event.getY(), context.currentHeldButtons, EnumMouseEvent.valueOf(event));
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getXStart() {
        return xStart;
    }

    @Override
    public int getYStart() {
        return yStart;
    }

    public EnumButton[] getButtons() {
        return buttons;
    }

    @Override
    public EnumMouseEvent getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MouseMovedEvent [x=" + x + ", y=" + y + ", xStart=" + xStart + ", yStart=" + yStart + ", buttons=" + Arrays.toString(buttons)
            + ", type=" + type + "]";
    }

    @Override
    public EnumKeyModifier[] getModifiers() {
        return EnumKeyModifier.NONE;
    }

}
