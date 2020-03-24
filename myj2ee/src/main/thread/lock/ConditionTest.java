package thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caichengcheng
 * date:2020-03-10
 */
public class ConditionTest {
    private static Lock lock  = new ReentrantLock();
    private static Condition con1 = lock.newCondition();
    private static Condition con2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i=0;i<5;i++){

                lock.lock();
                System.out.println("A");
                try {
                    con2.signal();
//                    TimeUnit.SECONDS.sleep(1);
                    con1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这里实验过后，发现没有 lock.unlock()  这行代码，程序也正常运行，原因是await方法已经释放了锁
//                lock.unlock();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<5;i++){
                lock.lock();
                System.out.println("B");
                try {
                    con1.signal();
//                    TimeUnit.SECONDS.sleep(1);
                    con2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
