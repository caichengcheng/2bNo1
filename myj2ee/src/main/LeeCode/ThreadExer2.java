package LeeCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExer2 {

    public static void main(String[] args) {
        Num num = new Num();
        new Thread(()->{
            num.sout();
        },"线程1").start();
        new Thread(()->{
            num.sout();
        },"线程2").start();
    }

    static class Num{
        private int MAX = 100;
        private volatile int num = 1;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void sout(){
            lock.lock();
            try {
                while (num<=MAX){
                    System.out.println("当前线程："+Thread.currentThread().getName()+"打印数字："+num);
                    num++;
                    condition.signalAll();
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } finally {
                lock.unlock();
            }

        }
    }
}
