package nio.nettyDay1;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * create by caichengcheng
 * date:2019-04-01
 *
 * BIO: blocking input output 阻塞式IO
 */
public class TestBIO {



    /**
     * 服务端：接收请求,服务端打印请求数据，并给客户端返回 智障语言
     */
    @Test
    public void timeServer(){
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8080);
            while(true){
                Socket socket  = serverSocket.accept();
                new Thread(()->{
                    System.out.println("accept a new request!");
                    BufferedReader in = null;
                    PrintWriter out = null;
                    try {
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream(),true);
                        while(true){
                            String data = in.readLine();
                            if(data == null)
                                 break;
                            System.out.println("request data = " + data);
                            data= data.replaceAll("？", "!");
                            out.println(data);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(in !=null){
                            try {
                                in.close();
                                System.out.println("输入流关闭");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(out !=null){
                            out.close();
                            System.out.println("输出流关闭");
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket !=null){
                try {
                    serverSocket.close();
                    System.out.println("socket关闭");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Socket socket = null ;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket("127.0.0.1",8080);
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                System.out.println("begin to request!");
                while(scanner.hasNext()){
                    String next = scanner.next();

                    out.println("client: " + next);
                    String result = in.readLine();
                    System.out.println("revice data from server:"+result);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(in !=null){
                    try {
                        in.close();
                        System.out.println("输入流关闭");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out !=null){
                    out.close();
                    System.out.println("输出流关闭");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                    System.out.println("socket关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
