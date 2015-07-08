package alexiil.utils.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import alexiil.utils.input.KeyList;
import alexiil.utils.logger.Logger;
import alexiil.utils.network.messages.MessageBase;

public class ServerMulti extends TimerTask implements IMessageSender {
    private int port;
    public Logger log = new Logger("net.server");
    private Map<Integer, ServerHandler> handlers = Collections.synchronizedMap(new HashMap<Integer, ServerHandler>());
    private Map<Integer, KeyList> clientKeys = Collections.synchronizedMap(new HashMap<Integer, KeyList>());
    private int nextHandler = 0;
    private Channel channel = null;
    
    public ServerMulti(int port) {
        this.port = port;
    }
    
    public synchronized void sendMessage(MessageBase message) {
        synchronized (handlers) {
            for (Handler handler : handlers.values())
                if (handler != null && handler.channel() != null)
                    handler.channel().writeAndFlush(message);
        }
    }
    
    public void sendMessage(int to, MessageBase message) {
        if (handlers.containsKey(to))
            handlers.get(to).channel().writeAndFlush(message);
    }
    
    public boolean remove(Handler handler) {
        clientKeys.remove(handler.getHandlerID());
        return handlers.remove(handler.getHandlerID()) != null;
    }
    
    public KeyList getKeyList(int handler) {
        return clientKeys.getOrDefault(handler, null);
    }
    
    public Handler getHandler(int index) {
        return handlers.getOrDefault(index, null);
    }
    
    public void run() {
        log.info("Starting server");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            final ServerMulti sm = this;
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override public void initChannel(SocketChannel ch) throws Exception {
                    ServerHandler handler = new ServerHandler(sm, nextHandler);
                    nextHandler++;
                    handlers.put(handler.getHandlerID(), handler);
                    clientKeys.put(handler.getHandlerID(), new KeyList(KeyList.mainList));
                    ch.pipeline().addLast(handler);
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            log.info("Binding to port...");
            ChannelFuture f;
            try {
                f = b.bind(port).sync();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            channel = f.channel();
            log.info("Server started");
            try {
                f.channel().closeFuture().sync();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finally {
            log.info("Stopping server (worker group)");
            workerGroup.shutdownGracefully();
            log.info("Stopping server (boss group)");
            bossGroup.shutdownGracefully();
            log.info("Stopped server");
        }
    }
    
    public void shutDownServer(String reason) {
        log.info("Closing server channel");
        sendMessage(new MessageQuit(reason));
        channel.close().syncUninterruptibly();
        log.info("Closed server channel");
    }
    
    public void shutdownServer() {
        shutDownServer("Server shut down!");
    }
}
