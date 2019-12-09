package thread.cyclicbarrier;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by caichengcheng
 * date:2019-03-23
 */
public class ConditionTest {


    public static void main(String [] args){
        TestAlternate testAlternate = new TestAlternate();
        Thread A = new Thread(()->{
            testAlternate.loopA();
        });
        A.setName("Thread -A");
        Thread B = new Thread(()->{
            testAlternate.loopB();
        });
        A.setName("Thread -B");
        Thread C = new Thread(()->{
            testAlternate.loopC();
        });
        A.setName("Thread -C");
        A.start();
        B.start();
        C.start();

    }
}
class TestAlternate{
    //线程执行顺序标记,1:表示loopA执行，2：表示loopB执行，3：表示loopC执行
    private volatile int number = 1;
    //获得lock锁
    private Lock lock = new ReentrantLock();
    //创建三个condition对象用来await(阻塞)和signal(唤醒)指定的线程
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    protected void loopA(){
        lock.lock();//上锁
        try {

            /*如果不是第一个标志位，就阻塞，为了解决虚假唤醒问题，使用while关键字
             */
            while(true){
                try {
                    if(number!=1) {
                        c1.await();//阻塞类似wait()
                    }
                    else {
                        System.out.println("A");
                        TimeUnit.MILLISECONDS.sleep(100);
                        number = 2;//使能第二个方法
                        c2.signal();//唤醒第二个线程,类似notify()方法
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            lock.unlock();//解锁
        }

    }

    protected void loopB(){
        lock.lock();//上锁
        try {

            //如果不是第一个标志位，就阻塞
            while(true){
                try {
                    if(number!=2) {

                        c2.await();//阻塞类似wait()
                    }
                    else {
                        System.out.println("B");
                        TimeUnit.MILLISECONDS.sleep(100);
                        number = 3;//使能第二个方法
                        c3.signal();//唤醒第二个线程,类似notify()方法
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            lock.unlock();//解锁
        }
    }
    protected void loopC(){
        lock.lock();//上锁
        try {

            while(true){
                try {
                    if(number!=3) {
                        c3.await();//阻塞类似wait()
                    }
                    else {
                        System.out.println("C");
                        TimeUnit.MILLISECONDS.sleep(100);
                        number = 1;//使能第二个方法
                        c1.signal();//唤醒第二个线程,类似notify()方法
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } finally {
            lock.unlock();//解锁
        }
    }
}
