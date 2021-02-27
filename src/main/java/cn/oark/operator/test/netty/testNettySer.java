package cn.oark.operator.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class testNettySer {
    public static void main(String[] args) throws Exception {
        //创建bossgroup workergroup
        //创建了这两个线程组
        //boss 只处理连接请求 worker 真正打工
        //两个都是无限循环的
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        //创建服务器端启动的参数
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)    //设置两个线程组
                    .channel(NioServerSocketChannel.class)        //绑定要使用的channel
                    .option(ChannelOption.SO_BACKLOG,128)   //设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)  //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建通道测试对象 这用的匿名对象
                        //给pipeline 增加处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //这里可以保存 socketChannel跟对应的用户
                            //根据客户标识放进集合，需要推送任务时，可以将业务加入到各个channel对应的NioEventGroup 的 taskQueue
                            ch.pipeline().addLast(new testNettyHandler());
                        }
                    }); //给worker 对应的管道，设置处理器
            System.out.println("服务器准备好了....");
            //启动服务器
            ChannelFuture cf = serverBootstrap.bind(6668).sync();  //绑定一个端口并同步 ，生成了一个ChannelFuture对象

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();  //异步模型相关
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
        }


    }
}
