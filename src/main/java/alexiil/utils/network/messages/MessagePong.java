package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import java.text.DecimalFormat;
import alexiil.utils.network.Handler;
import alexiil.utils.network.events.PingChangeEvent;

public class MessagePong extends MessageBase {
    public static final MessagePong parentMessage = new MessagePong(0L);
    /** The original ping that was sent in a ping message */
    public final long ping;
    private static final DecimalFormat format = new DecimalFormat();
    
    public MessagePong(long ping) {
        this.ping = ping;
    }
    
    public MessagePong(ByteBuf buffer) {
        ping = buffer.readLong();
    }
    
    @Override protected void writePayload(ByteBuf buffer) {
        buffer.writeLong(ping);
    }
    
    @Override public void onRecieve(Handler handler) {
        long current = System.nanoTime();
        handler.log.info("Recieved pong message: total ping = " + format.format(current - ping) + "ns (" + format.format((current - ping) / 1000000D) + "ms)");
        handler.setPing(ping, current);
        new PingChangeEvent(current - ping).post();
    }
    
    @Override public MessageBase getRecievingMessage(ByteBuf buffer) {
        return new MessagePong(buffer);
    }
    
    @Override public MessageBase getParent() {
        return parentMessage;
    }
}