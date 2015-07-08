package alexiil.utils.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.TimerTask;
import alexiil.utils.logger.Logger;
import alexiil.utils.network.messages.MessageBase;

public class Client extends TimerTask implements IMessageSender {
    private ClientHandler handler = null;
    private final String ip;
    private final int port;
    public final Logger log = new Logger("net.client");
    
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override public void initChannel(SocketChannel ch) throws Exception {
                    handler = new ClientHandler();
                    ch.pipeline().addLast(handler);
                }
            });
            ChannelFuture f = b.connect(ip, port).syncUninterruptibly();
            f.channel().closeFuture().syncUninterruptibly();
        }
        finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    public synchronized void sendMessage(MessageBase message) {
        handler.writeMessage(message);
    }
    
    public void stopClient(String reason) {
        sendMessage(new MessageQuit(reason));
        handler.channel().close().syncUninterruptibly();
    }
    
    public void stopClient() {
        stopClient("Client Disconnecting!");
    }
}
