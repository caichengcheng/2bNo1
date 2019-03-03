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
    /**
     *  一：ByteBuffer 继承自Buffer ,有几个属性要注意下下
     *      capacity：buffer的最大容量
     *      position: 当前的读取的位置
     *      limit：当前可读取的最大容量
     *      mark:可以认为是个标记，可通过reset()，将position 回置到 mark的位置
     *  二：关于ByteBuffer 对象的两种获取方式
     *  allocate(int capacity）和 allocateDirect(int capacity)
     *      allocate(int capacity）：构造了个HeapByteBuffer对象，在JVM的堆中创建一个内存空间来用于缓存区，
     *          优点：1、里面的数据会被垃圾回收
     *              2、因为数据存于JVM中，所以在jvm对里面的缓存区进行读写的时候，速度会快些
     *      allocateDirect(int capacity)：构造了个DirectByteBuffer对象，在当前的操作系统内存里，创建个内存空间用于缓存区
     *          优点：1、在对外部设备（io设备）打交道的时候速度较快，因为是数据直接放在操作系统的内存里，IO外设读取JVM的数据时，
     *          需要将JVM里的数据拷贝出来，再放进系统内存块中进行读取，DirectByteBuffer省去了这一步骤
     *      ps:为什么外设打交道要先把数据从堆内存中拷贝到堆外内存，再进行操作？
     *      答：因为若是直接进行操作，在期间JVM进行了gc，会将JVM内存空间进行标志-清楚-压缩（重排序），会引起buffer内数据的错乱
     */
    @Test
    public void test1(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        String str = "abcde";
        //put() 将数据放入缓存区中
        byteBuffer.put(str.getBytes(),0,str.length());
        System.out.println("position="+byteBuffer.position());
        System.out.println("limit="+byteBuffer.limit());
        System.out.println("capacity="+byteBuffer.capacity());

        LinkedList list = new LinkedList();
    }
}
