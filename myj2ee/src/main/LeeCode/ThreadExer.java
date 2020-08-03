package LeeCode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印121212121212
 */
public class ThreadExer {

    public static void main(String[] args) {
        Thread12 thread12 = new Thread12();
        new Thread(()->{
            for(int i=0; i<100; i++){
                thread12.sout1();
            }

        },"thread1").start();

        new Thread(()->{
            for(int i=0; i<100; i++){
                thread12.sout2();
            }

        },"thread2").start();
    }

    static class Thread12 {
        private int num = 1;
        private Lock lock = new ReentrantLock();
        private Condition cd = lock.newCondition();

        public void sout1()  {

            try {
                lock.lock();
                while (num==2){
                    cd.await();
                }
                System.out.println(1);
                num = 2;
                cd.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void sout2()  {

            try {
                lock.lock();
                while (num==1){
                    cd.await();
                }
                System.out.println(2);
                num = 1;
                cd.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }


    }


}


