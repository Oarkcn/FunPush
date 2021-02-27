package cn.oark.operator.test.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    //define one channel group to admin all channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws  Exception {
        Channel channel = ctx.channel();
        //add time push to other
        channelGroup.writeAndFlush("[Client] "+channel.remoteAddress() + " added!");
        channelGroup.add(channel);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }
}
