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

    //get锁
    private static ReentrantLock getLock = new ReentrantLock();

    //put锁
    private static ReentrantLock putLock = new ReentrantLock();

    //是否可以get条件
    private static Condition notEmpty = getLock.newCondition();

    //是否可以put条件
    private static Condition notFull = putLock.newCondition();

    //队列容量
    private final int capacity;

    //get的位置
    private AtomicInteger putIndex = new AtomicInteger(0);

    //put的位置
    private AtomicInteger getIndex = new AtomicInteger(0);

    //队列中元素的个数
    private AtomicInteger count = new AtomicInteger(0);

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
        getLock.lockInterruptibly();
        try {
            //队列为空，阻塞当前线程
            while (count.get() < 1) {
                notEmpty.await();
            }
            //获取get的位置，拿取元素
            int getPos = getIndex.getAndIncrement();
            Object result = queue[getPos];
            queue[getPos] = null;
            //到达队尾，重队头重新开始
            if ((getPos + 1) == capacity) {
                getIndex.set(0);
            }
            //队列元素个数-1，-1前队列已满，-1后唤醒阻塞的put线程
            if (count.getAndDecrement() == capacity) {
                notFull.signalAll();
            }
            return result;
        } finally {
            //释放锁
            getLock.unlock();
        }
    }

    /**
     * 入队
     * @param object
     * @throws InterruptedException
     */
    public void put(Object object) throws InterruptedException {
        //获取put锁
        putLock.lockInterruptibly();
        try {
            //队列已满，阻塞当前线程
            while (count.get() == capacity) {
                notFull.await();
            }
            //获取put的位置，放入元素
            int putPos = putIndex.getAndIncrement();
            queue[putPos] = object;
            //到达队尾，重队头重新开始
            if ((putPos + 1) == capacity) {
                putIndex.set(0);
            }
            //队列元素个数+1，+1前队列为空，+1后唤醒阻塞的get线程
            if (count.getAndIncrement() < 1) {
                signalNotEmpty();
            }
        }
        finally {
            //释放锁
            putLock.unlock();
        }
    }

    private static void signalNotEmpty() {
        getLock.lock();
        try {
            notEmpty.signal();
        } finally {
            getLock.unlock();
        }
    }

    private static void signalNotFull() {
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueue blockQueue = new BlockingQueue(10);
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0 ; i < 10 ; i++) {
            Customer customer = new Customer(blockQueue);
            Productor productor = new Productor(blockQueue,i);
            service.execute(customer);
            service.execute(productor);
        }
        service.shutdown();
    }
}
