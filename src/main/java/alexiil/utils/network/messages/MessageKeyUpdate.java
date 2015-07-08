package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import alexiil.utils.input.IKeyInput;
import alexiil.utils.network.Handler;
import alexiil.utils.network.ServerMulti;

public class MessageKeyUpdate extends MessageString {
    public static final MessageKeyUpdate parentMessage = new MessageKeyUpdate();
    public final boolean isPressed;
    
    protected MessageKeyUpdate() {
        super("Parent Key Update");
        isPressed = false;
    }
    
    public MessageKeyUpdate(IKeyInput key, String keyName) {
        super(keyName);
        isPressed = key.isPressed();
    }
    
    public MessageKeyUpdate(ByteBuf buffer) {
        super(buffer);
        isPressed = buffer.readBoolean();
    }
    
    @Override public void writePayload(ByteBuf buffer) {
        super.writePayload(buffer);
        buffer.writeBoolean(isPressed);
    }
    
    @Override public void onRecieve(Handler handler) {
        ServerMulti ser = handler.parent;
        if (ser == null)
            return;
        IKeyInput key = ser.getKeyList(handler.getHandlerID()).getKey(text);
        if (key.isPressed() && !isPressed)
            key.onRelease();
        else if (isPressed && !key.isPressed())
            key.onPressed();
    }
    
    @Override public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessageKeyUpdate(buffer);
    }
    
    @Override public MessageBase getParent() {
        return parentMessage;
    }
}
