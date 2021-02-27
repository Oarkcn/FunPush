package cn.oark.operator.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class testNio {
    public static void main(String[] args){
        //buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer存放数据
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);
        //取出数据
        intBuffer.flip();   //将buffer转换读写状态
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
        String str = "testtest测试测试😀";
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream("d:\\file1");
//            FileChannel fileChannel = fileOutputStream.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            buffer.put(str.getBytes(StandardCharsets.UTF_8));
//            buffer.flip();
//            try {
//                fileChannel.write(buffer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            File file = new File("d:\\file2");
//            FileInputStream newFile = new FileInputStream(file);
//            FileChannel fileChannel1 = newFile.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(Math.toIntExact(file.length()));
//            fileChannel1.read(buffer);
//            System.out.println(new String(buffer.array()));
//            newFile.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        File file = new File("d:\\file1");
//        File fileTO = new File("d:\\file2");
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            ByteBuffer buffer = ByteBuffer.allocate(4);
//            FileOutputStream out = new FileOutputStream(fileTO);
//            while(true){
//                int read = fileInputStream.getChannel().read(buffer);
//                if(read ==-1 )break;
//                buffer.flip();
//                out.getChannel().write(buffer);
//                buffer.clear();
//            }
//            fileInputStream.close();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
            //mappedbuffer
            //  可以让文件直接在堆外内存中修改
            //  操作系统不需要拷贝一次
//        try {
//            RandomAccessFile randomAccessFile = new RandomAccessFile("test1.txt","rw");
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);    //读取模式 起始位置 映射到内存的（可以直接修改的）范围长度
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//            InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
//
//            serverSocketChannel.socket().bind(inetSocketAddress);
//
//            ByteBuffer[] buffer = new ByteBuffer[2];
//            buffer[0] = ByteBuffer.allocate(5);
//            buffer[1] = ByteBuffer.allocate(3);
//
//            SocketChannel socketChannel = serverSocketChannel.accept();
//            int messageLength = 8;
//            while(true){
//                int byteRead = 0;
//                while (byteRead < messageLength){
//                    Long l = socketChannel.read(buffer);
//                    byteRead += l;
//                    System.out.println(byteRead );
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            serverSocketChannel.configureBlocking(false);//设置非阻塞模式
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册相应的类型
            while(true){
                if(selector.select(1000) == 0){
                    System.out.println("服务器等待了1s，无连接");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isAcceptable()){
                        System.out.println("新客户端连接我");
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false); //socketchannel也设置成非阻塞
                        socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(10)); //注册读事件和新buffer绑定到这个selector
                        next.cancel();
                    }else if (next.isReadable()){
                        try{
                            SocketChannel channel = (SocketChannel) next.channel();
                            ByteBuffer buffer = (ByteBuffer) next.attachment();
                            int read = channel.read(buffer);
                            while (read>0){
                                System.out.print("客户端发送为： " + new String(buffer.array()));
                                buffer.clear();
                                read = channel.read(buffer);
                            }
                            if (read == -1){
                                channel.close();
                            }
                            System.out.println("读取结束");
                        }catch (Exception e){
                            System.out.println("出现问题，关闭通道且注销关联的selector");
                            next.channel().close();
                            next.cancel();
                        }

                    }
                    //防止重复操作
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
