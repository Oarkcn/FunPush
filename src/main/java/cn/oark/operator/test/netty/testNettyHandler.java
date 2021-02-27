package cn.oark.operator.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


/*
    自定一个handler 需要继承netty 规定好的某个handler
    这时候才能成为一个handler
 */
public class testNettyHandler extends ChannelInboundHandlerAdapter {
    @Override
    //读取客户端发送来的数据
    /*
        ctx：上下文对象，含有管道，通道 ，地址
        msg 就是客户端发来的数据，默认是object
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx :" + ctx);
        System.out.println("server thread: "+Thread.currentThread().getName());
        //msg转成byteBuf
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("客户端发送消息是："+buffer.toString(StandardCharsets.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
        // 异步执行，提交该channel对应的nioenventloop 的taskqueue中
        //方案1 用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        //方案2 用户自定义定时任务 提交到scheduleTaskQueue 当中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },10, TimeUnit.SECONDS);
        //第三种 非当前reactor调用Channel的方法

    }
    /*
        代码读取完毕之后，回一个消息
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //数据写入缓存并刷新
        //一般来讲，对发送数据进行一个编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,", StandardCharsets.UTF_8));

    }
    /*异常处理

     */

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
