package thread.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caichengcheng
 * date:2020-03-12
 */
public class queue1 {
    private Object[] queue;
    private volatile int count ;
    private int cap;
    private int takeIndex;
    private int putIndex;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public queue1(int cap) {
        this.cap = cap;
        queue = new Object[cap];
    }

    public boolean put(Object obj) throws Exception{
        lock.lock();
        try{
            while (this.count == cap){
                notFull.await();
            }

        }finally {
            lock.unlock();
        }
        return false;
    }
}
