package thread.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by caichengcheng
 * date:2019-06-27
 */
public class ReentrantLockTest {
    static int num = 0;
    /**
     * 默认空参构造器是使用非公平锁：sync = new NonfairSync();
     */
    ReentrantLock lcokDefault = new ReentrantLock();
    /**
     * 带参构造器： sync = fair ? new FairSync() : new NonfairSync();
     */
    ReentrantLock lcokNonFair = new ReentrantLock(false);
    ReentrantLock lcokFair = new ReentrantLock(true);

    /**
     * 通过公平锁，进行原子加1
     * @return
     */
    int increateNumfair(){
        lcokDefault.lock();
        num++;
        lcokDefault.unlock();
        return num;
    }
    /**
     * 通过公平锁，进行原子加1
     * @return
     */
    int increateNumUonfair(){
        lcokFair.lock();
        num++;
        lcokFair.unlock();
        return num;
    }

    /**
     * 普通的lock - unlock
     */
    @Test
    public void testLockAndUnLock(){
        //============公平锁========
        for(int i=0;i<3;i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                increateNumfair();
                System.out.println(Thread.currentThread().getName()+"进行+1，结果："+num);
            },"公平锁线程"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(num);

    }
}
