package muyi.netty.demo.echo;

import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author: Yang Fan
 * @date: 2019/12/2
 * @desc: 铺平学习netty服务端启动
 */
public class FlatBootstrap {
    /*
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HelloWorldServerHandler());
                        }
                    });
            System.out.println("start the server");
            ChannelFuture f = b.bind(8800).sync();
            System.out.println("bind at 8800");
            f.channel().closeFuture().sync();
            System.out.println("server closed");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            System.out.println("finally.");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
     */

    private EventLoopGroup childGroup;

    private ReflectiveChannelFactory<? extends ServerChannel> channelFactory;

    private ChannelHandler childHandler;

    public void start() throws Throwable {
        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup childGroup = new NioEventLoopGroup();
        final ReflectiveChannelFactory<? extends ServerChannel> channelFactory =
                new ReflectiveChannelFactory<ServerChannel>(NioServerSocketChannel.class);
        final ChannelHandler childHandler = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new HelloWorldServerHandler());
            }
        };

        System.out.println("start the server");
        // doBind
        SocketAddress localAddress = new InetSocketAddress(8800);
        ServerChannel serverChannel = channelFactory.newChannel();
        // initChannel
        serverChannel.pipeline().addLast(new ChannelInitializer<ServerChannel>() {
            @Override
            protected void initChannel(ServerChannel serverChannel) throws Exception {
                System.out.println("init serverChannel start");
                serverChannel.eventLoop().execute(() -> {
                    serverChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("channelRead <- childChannel: " + msg.toString());
                            final Channel childChannel = (Channel) msg;
                            System.out.println("add childHandler");
                            childChannel.pipeline().addLast(childHandler);
                            System.out.println("register childChannel");
                            childGroup.register(childChannel);
                        }
                    });
                });
                System.out.println("init serverChannel end");
            }
        });

        ChannelFuture regFuture = bossGroup.register(serverChannel);
        ChannelPromise promise = serverChannel.newPromise();
        if (regFuture.isDone()) {
            // doBind0
            serverChannel.eventLoop().execute(() -> {
                serverChannel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            });
        } else {
            System.out.println("server channel not register done");
            regFuture.addListener((ChannelFutureListener) future -> {
                // doBind0
                serverChannel.eventLoop().execute(() -> {
                    serverChannel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                });
            });
        }
        ChannelFuture f = promise.sync();
        System.out.println("bind at 8800");
        f.channel().closeFuture().sync();
        System.out.println("server closed");
    }

    public static void main(String[] args) {
        try {
            new FlatBootstrap().start();
        } catch (Throwable t) {
            System.out.println("start error: ");
            t.printStackTrace();
        }
    }
}


