package alexiil.utils.input;

public class KeyToggle extends KeyPressed {
    public KeyToggle(int id, String name) {
        this(id, false, name);
    }

    public KeyToggle(int id, boolean pressed, String name) {
        super(id, name);
        if (pressed)
            onPressed();
    }

    @Override
    public void onPressed() {
        if (isPressed())
            super.onRelease();
        else
            super.onPressed();
    }

    @Override
    public void onRelease() {
        // Do nothing, this was already toggled...
    }

    @Override
    public IKeyInput duplicate() {
        return new KeyToggle(defaultKey, name);
    }
}
