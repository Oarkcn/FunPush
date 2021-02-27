package cn.oark.operator.test.groupchat;

import cn.oark.utility.config.SysPart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ChatClient {
    private static final String host = "127.0.0.1";
    private static final int port = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private static String username;
    public static void main(String[] args) throws Exception {
        boolean isConn = false;
        ChatClient chatClient = new ChatClient();
        new Thread(){
            public void run(){
                chatClient.getConn();
                while(true){
                    chatClient.read();
                    try{
                        Thread.currentThread().sleep(1000);

                    }catch (Exception e){

                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            System.out.println("发送中~");
            chatClient.send(s);
        }

    }
    private void read(){
        try{
            int readChannel = selector.select(2000);
            if(readChannel > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isReadable()){
                        SocketChannel sc = ((SocketChannel) next.channel());
                        ByteBuffer buffer = ByteBuffer.allocate(24);
                        int readNum = sc.read(buffer);
                        String msg = "";
                        byte[] msgBuf = new byte[24];
                        while (readNum>0){
                            buffer.flip();
                            buffer.get(0,msgBuf,0,readNum);
                            msg=msg + new String(msgBuf,0,readNum);
                            buffer.clear();
                            readNum = sc.read(buffer);
                        }
                        if(readNum==-1){
                            System.out.println("读取失败，销毁连接并重连");
                            try {
                                socketChannel.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            getConn();
                        }
                        System.out.println(new String(SysPart.base64Decoder.decode((msg))));
                    }
                    iterator.remove();
                }
            }else{
                //System.out.println("暂无新消息~");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private  void send(String msg){
        try {
            socketChannel.write(ByteBuffer.wrap(SysPart.base64Encoder.encode(msg.getBytes())));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送失败，销毁连接并重连");
            try {
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            getConn();
        }
    }
    private void getConn(){
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中");
            }
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString();
            System.out.println("连接成功 "+socketChannel.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
