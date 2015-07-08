package alexiil.utils.network;

public class ServerHandler extends Handler {
    public ServerHandler(ServerMulti parent, int handlerID) {
        super(ESide.SERVER, parent, handlerID);
    }
}
