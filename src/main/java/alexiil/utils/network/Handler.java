package alexiil.utils.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import alexiil.utils.logger.Logger;
import alexiil.utils.network.events.EventHandlerAdded;
import alexiil.utils.network.events.EventHandlerRemoved;
import alexiil.utils.network.messages.MessageBase;
import alexiil.utils.network.messages.MessageByte;
import alexiil.utils.network.messages.MessagePing;
import alexiil.utils.network.messages.MessageString;

public abstract class Handler extends ByteToMessageDecoder {
    public final Logger log;
    protected Channel channel = null;
    public final ESide side;
    public final ServerMulti parent;
    public final int handlerID;
    private long totalLatency;
    public static enum ESide {
        SERVER, CLIENT;
    }
    
    public Handler(ESide s, ServerMulti par, int handlerID) {
        side = s;
        log = new Logger("net.handler." + side.name().toLowerCase());
        parent = par;
        this.handlerID = handlerID;
    }
    
    public Channel channel() {
        return channel;
    }
    
    public void writeMessage(MessageBase message) {
        channel.writeAndFlush(message);
    }
    
    public void writeByte(byte b) {
        writeMessage(new MessageByte(b));
    }
    
    public void flush() {
        channel.flush();
    }
    
    public int getHandlerID() {
        return handlerID;
    }
    
    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Exception thrown");
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        log.info("Closing Channel...");
        ctx.close();
        log.info("Closed Channel.");
    }
    
    @Override public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("Handler Added");
        new EventHandlerAdded(this).post();
    }
    
    @Override protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
        log.info("Handler Removed");
        new EventHandlerRemoved(this).post();
        if (parent != null)
            parent.remove(this);
    }
    
    @Override public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel Registered");
    }
    
    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        writeMessage(new MessageString("Debug message. Hopefully, this should NOT be recieved on the " + side.name().toLowerCase() + "."));
        writeMessage(new MessagePing());
        flush();
    }
    
    @Override public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        if (msg == null)
            return;
        if (msg instanceof MessageBase)
            ((MessageBase) msg).writeMessage(byteBuf);
        ctx.write(byteBuf, promise);
    }
    
    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 9)// NO valid messages exist if the length is that small (8 bytes is used for the
                                   // length, and the packetID)
            return;
        try {
            MessageBase message = MessageBase.getMessage(in);
            if (message == null)
                return;
            out.add(message);
            message.onRecieve(this);
        }
        catch (Throwable t) {
            
        }
    }
    
    /**
     * @param ping
     *            When this handler SENT the message ping
     * @param pong
     *            When the handler AT THE OTHER END of the connection SENT the message pong
     * @param current
     *            When this handler RECIEVED the pong message
     */
    public void setPing(long ping, long current) {
        totalLatency = ping - current;
    }
    
    public long getTotalLatency() {
        return totalLatency;
    }
}
