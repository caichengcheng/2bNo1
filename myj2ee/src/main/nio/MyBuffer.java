package nio;

import java.nio.ByteBuffer;
import java.util.LinkedList;

import org.junit.Test;

/**
 * @author caichengcheng
 *         time: 2019/3/3.
 * NIO 的构成：通道（建立文件和程序的连接）+缓冲区（装载数据）
 *
 * 缓冲区：ByteBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer、CharBuffer
 * 8种基本数据类型中没有boolean类型的缓存区
 *
 */
public class MyBuffer {
    @Test
    public void test1(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        LinkedList list = new LinkedList();
    }
}
