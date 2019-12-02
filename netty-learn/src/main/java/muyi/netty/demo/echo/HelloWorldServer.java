package muyi.netty.demo.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: Yang Fan
 * @date: 2019/12/2
 * @desc:
 */
public class HelloWorldServer {


    public static void main(String[] args) {
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
    }

}
