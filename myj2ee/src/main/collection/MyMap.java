package collection;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by caichengcheng
 * date:2019-03-19
 */
public  class MyMap {
    @Test
    public void test1(){
        HashMap<Object, Object> hashMap = new HashMap<>();
        Hashtable<Object, Object> hashtable = new Hashtable<>();

//        MyMap a = null ;
//        MyMap b = null ;
//        System.out.println(a == b);

        ReentrantLock lock = new ReentrantLock(false);
        for(int i=0;i<3;i++){
            Thread t = new Thread(()->{
                lock.lock();
                try {
                    Thread.currentThread().sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                }catch (Exception e){
                    e.printStackTrace();
                }
                lock.unlock();

            });
            t.setName("thread i="+i);
            t.start();
//            AtomicInteger
        }

    }

    public static void main(String[] args) {
        HashMap<Object, Object> m1 = new HashMap<>(13);
        System.out.println(m1.size());
        m1.put(1,1);
        System.out.println(m1.size());
    }



}
