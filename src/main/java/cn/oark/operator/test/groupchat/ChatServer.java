package cn.oark.operator.test.groupchat;

import cn.oark.utility.config.SysPart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class ChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private final int port = 6667;
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.listen();
    }
    public ChatServer(){
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(port));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void listen(){
        try {
            while(true){
                int count = selector.select(2000);
                if(count > 0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector,SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "上线了");
                            send(sc,sc.getRemoteAddress() + "上线了");
                            //selectionKey.cancel();
                        }else if(selectionKey.isReadable()){
                            String msg = read(selectionKey);
                            System.out.println("服务器获取到消息，内容为 " + msg);
                            send((SocketChannel) selectionKey.channel(),msg);
                        }
                        iterator.remove();
                    }
                }else{
                    //System.out.println("Waiting");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String read(SelectionKey selectionKey){
        SocketChannel socketChannel = null;
        String msgin = "";
        try{
            socketChannel =(SocketChannel) selectionKey.channel();
            ByteBuffer msg = ByteBuffer.allocate(24);
            byte[] msgBuf = new byte[24];
            int count = socketChannel.read(msg);
            while(count>0){
                msg.flip();
                msg.get(0,msgBuf,0,count);
                msgin=msgin + new String(msgBuf,0,count);
                 msg.clear();

                count = socketChannel.read(msg);
            }
            if (count == -1){
                System.out.println("连接异常，断开与 "+ socketChannel.getRemoteAddress() +" 的连接");
                selectionKey.cancel();
                socketChannel.close();
                return socketChannel.getRemoteAddress() + " 下线了";
            }else{
                return "From:"+socketChannel.getRemoteAddress()+ " Says: "+ new String(SysPart.base64Decoder.decode((msgin)));
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println(socketChannel.getRemoteAddress()+"连接失败");

                msgin = socketChannel.getRemoteAddress() + " 下线了";
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return msgin;
            }
        }
        return msgin;
    }
    private boolean send(SocketChannel self, String msg){
        System.out.println("转发消息中...");
        byte[] msgByte = SysPart.base64Encoder.encode(msg.getBytes(StandardCharsets.UTF_8));
        for (SelectionKey key : selector.keys()){
            Channel channel = key.channel();
            if(channel instanceof SocketChannel & channel != self){
                try {
                    ((SocketChannel) channel).write(ByteBuffer.wrap(msgByte));
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("发送异常，清除channel");
                    key.channel();
                    try {
                        channel.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
        return  false;
    }
}
