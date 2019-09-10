package thread.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自己实现一个阻塞队列
 * create by caichengcheng
 * date:2019-05-19
 */
public class BlockingQueue {
    //队列，先进先出
    private final Object[] queue;

    private static ReentrantLock lock = new ReentrantLock();

    //是否可以get条件
    private  Condition notEmpty = lock.newCondition();

    //是否可以put条件
    private  Condition notFull = lock.newCondition();

    //队列容量
    private final int capacity;

    //get的位置
    private int putIndex ;

    //put的位置
    private int getIndex ;

    //队列中元素的个数
    private int count ;

    /**
     * 初始化队列
     * @param capacity 队列容量
     */
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
    }

    /**
     * 出队
     * @return
     * @throws InterruptedException
     */
    public Object get() throws InterruptedException {
        //获取get锁
        lock.lockInterruptibly();
        try {
            //队列为空，阻塞当前线程
            while (count < 1) {
                notEmpty.await();
            }
            //获取get的位置，拿取元素
            Object result = queue[getIndex];
            queue[getIndex] = null;
            //到达队尾，重队头重新开始
            if (++getIndex == capacity) {
                getIndex=0;
            }
            count-- ;
            notFull.signal();
            return result;
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 入队
     * @param object
     * @throws InterruptedException
     */
    public void put(Object object) throws InterruptedException {
        //获取put锁
        lock.lockInterruptibly();
        try {
            //队列已满，阻塞当前线程
            while (count == capacity) {
                notFull.await();
            }
            //获取put的位置，放入元素
            queue[putIndex] = object;
            //到达队尾，重队头重新开始
            if (++putIndex == capacity) {
                putIndex = 0;
            }
            //队列元素个数+1，+1前队列为空，+1后唤醒阻塞的get线程
            count++;
            notEmpty.signal();
        }
        finally {
            //释放锁
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BlockingQueue blockQueue = new BlockingQueue(10);
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0 ; i < 13 ; i++) {
            Customer customer = new Customer(blockQueue);
            Productor productor = new Productor(blockQueue,i);
            service.execute(customer);
            service.execute(productor);
        }
        service.shutdown();
    }
}
