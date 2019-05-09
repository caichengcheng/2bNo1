package thread.cyclicbarrier;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * create by caichengcheng
 * date:2019-01-25
 */
public class CyclicBarrierTest1 {
    /**
     * 执行结果
     * 主线程 执行到await
     * 线程1 执行到await
     * 线程1 执行完毕
     * 主线程 执行完毕
     */
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(()->{

            try {
                System.out.println("线程1 执行到await");
                c.await();
                System.out.println("线程1 执行完毕");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            System.out.println("主线程 执行到await");
            c.await();
            System.out.println("主线程 执行完毕");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
