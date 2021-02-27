package cn.oark.operator.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testBio {
    public static void main(String[] args) throws Exception{
        //新建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //有新连接就创建新线程
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server start");
        while (true){
            //监听 等待连接
            final Socket socket = serverSocket.accept();
            System.out.println("Linked!");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }
    public static void handler(Socket socket){
        try{
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println( Thread.currentThread().getName() + " |- " + new String(bytes) );
                }else{
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("End link!");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
