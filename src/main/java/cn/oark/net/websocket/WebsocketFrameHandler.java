package cn.oark.net.websocket;

import cn.oark.utility.config.SysPart;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("["+ctx.channel().remoteAddress() +"]"+"消息内容为："+msg.text());
        SysPart.onlineChannelGroup.writeAndFlush(new TextWebSocketFrame("["+ctx.channel().remoteAddress() +"]"+"消息内容为："+msg.text()));
        //json反序列化


        //信息合法性校验


        //合法信息封包入栈
    }
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        SysPart.onlineChannelGroup.add(ctx.channel());
        System.out.println("[上线通知]"+ctx.channel().remoteAddress()+"上线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[下线通知]"+ctx.channel().remoteAddress()+"下线了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误！"+cause.getMessage());
        ctx.close();
    }
}
