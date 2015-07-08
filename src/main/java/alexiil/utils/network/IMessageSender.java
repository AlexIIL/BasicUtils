package alexiil.utils.network;

import alexiil.utils.network.messages.MessageBase;

public interface IMessageSender {
    public abstract void sendMessage(MessageBase message);
}
