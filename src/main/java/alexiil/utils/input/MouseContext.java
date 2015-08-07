package alexiil.utils.input;

public class MouseContext {
    public boolean invertMouseButtons;
    EnumButton[] currentHeldButtons = new EnumButton[0];
    int xStart, yStart;

    public IMouseEvent changeFor(IMouseEvent event) {
        if (event instanceof MouseStateChangeEvent) {
            EnumButton button = ((MouseStateChangeEvent) event).getButton();
            if (event.getType() == EnumMouseEvent.PRESSED) {
                if (button == EnumButton.PRIMARY) {// You can only drag with the primary
                    xStart = event.getX();
                    yStart = event.getY();
                }
                currentHeldButtons = button.add(currentHeldButtons);
            } else if (event.getType() == EnumMouseEvent.RELEASED) {
                currentHeldButtons = button.remove(currentHeldButtons);
            }
            if (event.getType() != EnumMouseEvent.PRESSED) {
                if (button == EnumButton.PRIMARY) {
                    event = new MouseStateChangeEvent(event.getX(), event.getY(), xStart, yStart, button, event.getType(), event.getModifiers());
                }
            }
        }
        if (event instanceof MouseMovedEvent && EnumButton.PRIMARY.isInArray(currentHeldButtons)) {
            MouseMovedEvent old = (MouseMovedEvent) event;
            return new MouseMovedEvent(old.getX(), old.getY(), xStart, yStart, currentHeldButtons, old.getType());
        } else {
            return event;
        }
    }
}
