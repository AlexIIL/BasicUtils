package alexiil.utils.input;

import java.util.concurrent.atomic.AtomicBoolean;
import alexiil.utils.config.Option;

public class KeyPressed extends Option implements IKeyInput {
    public final int defaultKey;
    private final AtomicBoolean pressed = new AtomicBoolean(false);
    public final String name;
    
    public KeyPressed(int id, String name) {
        super(name, name, id);
        defaultKey = id;
        this.name = name;
        KeyList.mainList.addKey(name, this);
    }
    
    @Override public boolean matches(int id) {
        return id == getAsInt();
    }
    
    public void setKey(int id) {
        setValue(id);
    }
    
    @Override public boolean isPressed() {
        return pressed.get();
    }
    
    @Override public void onPressed() {
        pressed.set(true);
    }
    
    @Override public void onRelease() {
        pressed.set(false);
    }
    
    @Override public boolean dePress() {
        if (isPressed()) {
            pressed.set(false);
            return true;
        }
        return false;
    }
    
    @Override public int getID() {
        return getAsInt();
    }
    
    @Override public void resetKey() {
        setValue(defaultKey);
    }
    
    @Override public boolean isShown() {
        return false;
    }
    
    @Override public String getName() {
        return name;
    }
    
    @Override public IKeyInput duplicate() {
        return new KeyPressed(defaultKey, name);
    }
}