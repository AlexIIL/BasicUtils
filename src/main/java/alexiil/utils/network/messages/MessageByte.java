package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import alexiil.utils.network.Handler;

/**
 * Simple class for debugging
 * 
 * @author AlexIIL
 */
public class MessageByte extends MessageBase {
    public static final MessageByte parentMessage = new MessageByte((byte) 0);
    public final byte payload;
    
    public MessageByte(byte bte) {
        payload = bte;
    }
    
    public MessageByte(ByteBuf bytes) {
        payload = bytes.readByte();
    }
    
    @Override
    protected void writePayload(ByteBuf buffer) {
        buffer.writeByte(payload);
    }
    
    @Override
    public String toString() {
        return "Message Byte:" + Integer.toString(payload);
    }
    
    @Override
    public void onRecieve(Handler handler) {
        log.info(handler + ":" + toString());
    }
    
    @Override
    public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessageByte(buffer);
    }
    
    @Override
    public MessageBase getParent() {
        return parentMessage;
    }
}