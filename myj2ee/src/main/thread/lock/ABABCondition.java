package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABABCondition {
    static ReentrantLock lock = new ReentrantLock();
    static Condition con1 = lock.newCondition();
    static Condition con2 = lock.newCondition();
    static volatile int count = 0;
    public static void main(String[] args) {
        Thread  t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    print(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("奇数线程");

        Thread  t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    print(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.setName("偶数线程");

        t2.start();
        t1.start();
    }

    public static void print(int tag) throws InterruptedException {
        lock.lock();
        if(tag == 1){
            if((count & 1) == 1){
                System.out.println( count++ + " " + Thread.currentThread().getName());
            }
            con2.signal();
            con1.await();
        }else{
            if((count & 1) == 0){
                System.out.println(count++ + " " + Thread.currentThread().getName());
            }
            con1.signal();
            con2.await();
        }
        lock.unlock();
    }
}
