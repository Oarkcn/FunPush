package cn.oark.operator.test.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;


public class httpHandler extends SimpleChannelInboundHandler<HttpObject> {
    //读取客户端的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg 是不是httpRequest 请求
        System.out.println(msg.getClass());
        System.out.println(ctx.channel().remoteAddress());
        System.out.println(((HttpRequest) msg).uri());
        if(msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(((HttpRequest) msg).uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("no response");
                return;
            }
            ByteBuf content = Unpooled.copiedBuffer("Hello ,this is Server", StandardCharsets.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
