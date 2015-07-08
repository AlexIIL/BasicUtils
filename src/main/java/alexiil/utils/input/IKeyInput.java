package alexiil.utils.input;

public interface IKeyInput {
    public boolean matches(int id);
    
    public void onPressed();
    
    public void onRelease();
    
    public boolean isPressed();
    
    public boolean dePress();
    
    public int getID();
    
    public void setKey(int id);
    
    public void resetKey();
    
    public String getName();
    
    public IKeyInput duplicate();
}
