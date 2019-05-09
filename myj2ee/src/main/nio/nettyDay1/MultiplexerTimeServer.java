package nio.nettyDay1;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * create by caichengcheng
 * date:2019-04-02
 */
public class MultiplexerTimeServer implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    //服务端是否停止接收请求
    private volatile boolean stop = true;

    public MultiplexerTimeServer(int port) {
        try {
            //初始化选择器
            selector = Selector.open();
            //初始化服务端通道
            serverSocketChannel = ServerSocketChannel.open();
            //通道设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //服务间通信依赖一对socket，这里初始化服务端socket，绑定域名+端口，并且backlog-总的连接数量
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            //将通道注册到选择器，TODO SelectionKey 待学习
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start!");

        } catch (IOException e) {
            e.printStackTrace();
            //停止虚拟机
            System.exit(1);
        }
    }


    @Override
    public void run() {
        while (!stop){
            try {
                //选择器的select():当有处于就绪状态的channel时，selector将返回该channel的selectionKey集合
//                selector.select();
                //选择器的select(long timeout): 无论是否有读写等事件发生，selector每隔timeout 毫秒 时间都会被唤醒，在这里，就是每隔1秒被唤醒
                selector.select(1000);
                /**
                 * 关于selectionKeys：
                 * 1.每个Channel向Selector注册时,都将会创建一个selectionKey
                 * 2.选择键将Channel与Selector建立了关系,并维护了channel事件
                 * 3.可以通过cancel方法取消键,取消的键不会立即从selector中移除,而是添加到cancelledKeys中,在下一次select操作时移除它.
                 * 所以在调用某个key时,需要使用isValid进行校验
                 */
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        e.printStackTrace();
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 真正的处理请求
     * @param key
     */
    private void handleInput(SelectionKey key) throws IOException {
        //当前的selectionKey是否有效
        if(key.isValid()){
            //是否是可接收新连接-连接就绪
            if(key.isAcceptable()){
                //通过选择键，获取对应的通道
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                //通道accept
                SocketChannel accept = ssc.accept();
                //设置通道非阻塞
                accept.configureBlocking(false);
                //通道注册进选择器
                accept.register(selector,SelectionKey.OP_READ);
            }
            //当前是否是可读-读就绪
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel)key.channel();
                //获取缓存区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){

                    //缓冲区 切换成读模式
                    readBuffer.flip();
                    //readBuffer.remaining()：limit-position = 还未读的数据长度
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //从缓冲区 读数据
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("revice data from buffer:" + body);

                    doWrite(sc,System.currentTimeMillis()+"");
                }else if(readBytes < 0){
                    //读取字节数<0 ，即值为-1，链路已经关闭，需要关闭SocketChannel需要释放资源
                    key.cancel();
                    sc.close();
                }else {
                    //读取字节数=0：正常场景
                    ;
                }
            }
        }
    }

    /**
     * 写数据返回给客户端
     * @param channel
     * @param response
     */
    private void doWrite(SocketChannel channel , String response) throws IOException {
        if(StringUtils.isNotBlank(response)){
            byte[] bytes = response.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            //将返回的数据放入缓冲区
            buffer.put(bytes);
            //切换缓冲区为读模式
            buffer.flip();
            //缓冲区放入
            channel.write(buffer);

        }
    }
}
