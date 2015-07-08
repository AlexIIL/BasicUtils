package alexiil.utils.network.messages;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import alexiil.utils.logger.Logger;
import alexiil.utils.network.Handler;
import alexiil.utils.network.MessageQuit;

public abstract class MessageBase {
    protected static Logger log = new Logger("net.message");
    private static final Map<Integer, MessageBase> intToMessage = Collections.synchronizedMap(new HashMap<Integer, MessageBase>());
    private static final Map<MessageBase, Integer> messageToInt = Collections.synchronizedMap(new HashMap<MessageBase, Integer>());
    private static boolean isInInit = false;
    private static int currentMessageNumber = 0;
    private final int packetID;
    
    public static void init() {
        isInInit = true;
        currentMessageNumber = 0;
        intToMessage.clear();
        messageToInt.clear();
        addMessage(MessageQuit.parentMessage);
        addMessage(MessagePing.parentMessage);
        addMessage(MessagePong.parentMessage);
        addMessage(MessageByte.parentMessage);
        addMessage(MessageString.parentMessage);
        addMessage(MessageKeyUpdate.parentMessage);
    }
    
    /**
     * @param message
     *            The message object that will be used to construct new messages (for receiving)
     * @return
     */
    public static boolean addMessage(MessageBase message) {
        if (!isInInit) {
            log.warn("Someone tried to add a message NOT during the init phase! (" + message.getClass().toString() + ")");
            Throwable t = new Throwable("Someone tried to add a message NOT during the init phase! (" + message.getClass().toString() + ")");
            t.printStackTrace();
            return false;
        }
        if (message == null) {
            log.warn("Someone tried to add a message that was null!");
            Throwable t = new Throwable("Someone tried to add a message that was null!");
            t.printStackTrace();
            return false;
        }
        log.info("Registered a new message type (Id = " + currentMessageNumber + ", message = " + message.getClass().getName() + ")");
        intToMessage.put(currentMessageNumber, message);
        messageToInt.put(message, currentMessageNumber);
        currentMessageNumber++;
        return true;
    }
    
    public static void postInit() {
        isInInit = false;
    }
    
    public static MessageBase getMessage(ByteBuf bytes) throws DecoderException {
        int length = bytes.getInt(bytes.readerIndex());
        if (length < 4)
            return null;// throw new
                        // DecoderException("The packet length is less than 4, so not enough to determine the packetID!");
        if (bytes.readableBytes() < length)
            return null;// throw new DecoderException("The Packet was not long enough!");
        int packetID = bytes.getInt(bytes.readerIndex() + 4);
        if (packetID < 0 || packetID > intToMessage.size())
            throw new DecoderException("The packetID was not in the mapping!");
        bytes.readInt();
        bytes.readInt();
        MessageBase clazz = intToMessage.get(packetID);
        ByteBuf payload = bytes.readBytes(length - 8);
        return clazz.getRecievingMessage(payload);
    }
    
    /**
     * 
     * @param parentMessage
     *            The object that was registered in the {@link #addMessage(MessageBase)} method (used for getting the
     *            packet ID) If this is null, then it is expected to be the parent. (and the packetID is set to -1)
     */
    public MessageBase() {
        if (getParent() == null || getParent() == this)
            packetID = -1;
        else {
            Map<MessageBase, Integer> map = messageToInt;
            if (map == null)
                System.out.println("map WAS NULL!!! OMG!");
            MessageBase par = getParent();
            if (par == null)
                System.out.println("par WAS NULL!!! OMG!");
            if (!map.containsKey(par))
                System.out.println("map DOES NOT COANTAIN A VALUE FOR par");
            packetID = map.get(par);
        }
    }
    
    protected abstract void writePayload(ByteBuf buffer);
    
    public abstract void onRecieve(Handler handler);
    
    /**
     * This should construct a new message (most likely of the same class) that can read the buffer
     */
    public abstract MessageBase getRecievingMessage(ByteBuf buffer);
    
    public abstract MessageBase getParent();
    
    public final void writeMessage(ByteBuf bytes) {
        ByteBuf payload = Unpooled.buffer();
        writePayload(payload);
        int length = payload.readableBytes() + 8;
        bytes.writeInt(length);
        bytes.writeInt(packetID);
        bytes.writeBytes(payload);
    }
}
