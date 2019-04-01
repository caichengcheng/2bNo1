package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * create by caichengcheng
 * date:2019-03-08
 */
public class LoadWebPageUseSelector {

    //通过Selector同时下载
    public void load(Set<URL> urls) throws IOException {

        Map<SocketAddress, String> mapping = urlToSocketAddress(urls);
        System.out.println(mapping);

        //创建Selector
        Selector selector = Selector.open();

        //将套接字的Channel注册到Selector上
        for(SocketAddress address : mapping.keySet()) {
            register(selector, address);
        }

        int finished = 0;
        int total = mapping.size(); //总的连接个数

        //接收数据的buffer
        ByteBuffer buffer = ByteBuffer.allocate(32 * 1024); //单位（字节）
        int len = -1;

        while(finished < total) {
            selector.select(); //会阻塞
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();  //移除key
                if(key.isValid() && key.isConnectable()) {
                    //channel连接成功
                    SocketChannel channel = (SocketChannel) key.channel();
                    //如果连接成功
                    boolean success = channel.finishConnect();
                    if(!success) {
                        finished++;
                        key.cancel();
                    } else {
                        InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                        String path = mapping.get(address); //获得地址路径
                        String request = "GET " + "/ HTTP/1.1" + "\r\n\r\n";
                        System.out.println(request);
                        ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF-8"));
                        //发送
                        channel.write(header);
                    }
                } else if(key.isValid() && key.isReadable()) {
                    //channel可读
                    SocketChannel channel = (SocketChannel) key.channel();
                    InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                    String fileName = address.getHostName() + ".txt";
                    FileChannel fileChannel = FileChannel.open(Paths.get(fileName),
                            StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    buffer.clear();

                    while((len = channel.read(buffer)) > 0 || buffer.position() != 0) {
                        buffer.flip(); //转换成读模式
                        fileChannel.write(buffer);  //向文件channel写数据
                        buffer.compact();
                    }

                    if(len == -1) {
                        finished++;
                        key.cancel();
                    }
                }
            }
        }

    }


    /**
     * 将套接字注册到Selector上
     * @param selector
     * @param socketAddress
     */
    private void register(Selector selector, SocketAddress socketAddress) throws IOException {

        SocketChannel channel = SocketChannel.open();

        //设置Channel为非阻塞模式
        channel.configureBlocking(false);
        channel.connect(socketAddress);

        //将channel注册到selector上
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
    }


    /**
     * url转SocketAddress
     * @param urls
     * @return
     */
    private Map<SocketAddress, String> urlToSocketAddress(Set<URL> urls) {

        Map<SocketAddress, String> map = new HashMap<>();
        for(URL url : urls) {
            int port;
            if(url.getPort() != -1) {
                port = url.getPort();
            } else {
                port = url.getDefaultPort();
            }

            SocketAddress address = new InetSocketAddress(url.getHost(), port);
            String path = url.getPath();
            if(url.getQuery() != null) {
                path = path + "?" + url.getQuery();
            }

            map.put(address, path);
        }

        return map;
    }
}