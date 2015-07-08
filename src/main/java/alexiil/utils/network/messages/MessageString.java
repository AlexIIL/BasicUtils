package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import alexiil.utils.network.Handler;

/**
 * Simple class for debugging
 * 
 * @author AlexIIL
 */
public class MessageString extends MessageBase {
    public static final MessageString parentMessage = new MessageString("Parent String");
    public final String text;
    
    public MessageString(String string) {
        text = string;
    }
    
    public MessageString(ByteBuf bytes) {
        int length = bytes.readInt();
        text = new String(bytes.readBytes(length).array());
    }
    
    @Override
    public void writePayload(ByteBuf buffer) {
        byte[] bytes = text.getBytes();
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
    }
    
    @Override
    public String toString() {
        return "Message String:" + text;
    }
    
    @Override
    public void onRecieve(Handler handler) {
        log.info(handler + ":" + toString());
    }
    
    @Override
    public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessageString(buffer);
    }
    
    @Override
    public MessageBase getParent() {
        return parentMessage;
    }
}
