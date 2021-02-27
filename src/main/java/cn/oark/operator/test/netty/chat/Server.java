package cn.oark.operator.test.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    public static void main(String[] args) {
        new Server().run();
    }
    public void run(){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(4);
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{

            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ServerInit());
            System.out.println("started!");
            ChannelFuture future = bootstrap.bind(8000).sync();
            ChannelFuture future1 = future.channel().closeFuture().sync();
        }catch (Exception exception){
            System.out.println(exception);
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
