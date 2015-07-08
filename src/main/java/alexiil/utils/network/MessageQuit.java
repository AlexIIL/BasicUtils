package alexiil.utils.network;

import io.netty.buffer.ByteBuf;
import alexiil.utils.network.messages.MessageBase;
import alexiil.utils.network.messages.MessageString;

public class MessageQuit extends MessageString {
    public static final MessageQuit parentMessage = new MessageQuit("Parent Quit Message");
    
    public MessageQuit(String reason) {
        super(reason);
    }
    
    public MessageQuit(ByteBuf buffer) {
        super(buffer);
    }
    
    @Override public void writePayload(ByteBuf buffer) {
        super.writePayload(buffer);
    }
    
    @Override public void onRecieve(Handler handler) {}
    
    @Override public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessageQuit(buffer);
    }
    
    @Override public MessageBase getParent() {
        return parentMessage;
    }
}
