package nio.nettyDay1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * create by caichengcheng
 * date:2019-04-03
 */
public class TimeClientHandle implements Runnable{
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private boolean stop;

    public TimeClientHandle(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //多路复用器关闭后，所有注册在上面的channel和pipe 等资源都会被自动地去注册并关闭，所以不需要重复释放资源
        if(selector!=null){
            try {
                selector.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        //key 有效
        if(key.isValid()){
            SocketChannel sc = (SocketChannel)key.channel();
            //key 连接就绪
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ);
                    doWrite(sc);
                }else{
                    //连接失败，进程退出
                    System.exit(1);
                }
            }

            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes>0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    String body = new String(bytes,"utf-8");
                    System.out.println("now is :" + body);
                    this.stop = true;
                }else if(readBytes <0){
                    key.cancel();
                    sc.close();
                }else{
                    ;
                }

            }
        }
    }

    private void doConnect() throws IOException {
        //如果连接成功，则注册到多路复用器上，发送请求消息，读应答
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            //注册到选择器
            socketChannel.register(selector, SelectionKey.OP_READ);
            //发送请求消息
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel sc) throws IOException {
        byte[] requestStr = "hi server".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(requestStr.length);
        writeBuffer.put(requestStr);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            System.out.println("request over");
        }

    }
}
