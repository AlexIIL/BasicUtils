package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import java.util.Calendar;
import java.util.TimeZone;
import alexiil.utils.network.Handler;

public class MessagePing extends MessageBase {
    public static final Calendar constCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    public static final MessagePing parentMessage = new MessagePing();
    public final long ping;
    
    public MessagePing() {
        ping = System.nanoTime();
    }
    
    public MessagePing(ByteBuf buf) {
        ping = buf.readLong();
    }
    
    @Override protected void writePayload(ByteBuf buffer) {
        buffer.writeLong(ping);
    }
    
    @Override public void onRecieve(Handler handler) {
        handler.writeMessage(new MessagePong(ping));
    }
    
    @Override public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessagePing(buffer);
    }
    
    @Override public MessageBase getParent() {
        return parentMessage;
    }
}
