package cn.oark.operator.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class testNioClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress =(new InetSocketAddress("127.0.0.1",6666));
        if(!socketChannel.connect(inetSocketAddress)) {
            System.out.println("连接需要事件，可继续处理别的事务");
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中");
            }
        }
        System.out.println("连接成功 "+socketChannel.hashCode());
        String s =  "asd"; //"发送的数据发送的数据发送的数据发送的数据发送的数据发送的数据发送的数据发送的数据发送的数据";

        ByteBuffer buffer = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(buffer);
        //socketChannel.close();
        System.in.read();
        buffer = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(buffer);
        System.in.read();
        buffer = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(buffer);
        System.in.read();
        buffer = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
