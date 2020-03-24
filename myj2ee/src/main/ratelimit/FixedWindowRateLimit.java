package ratelimit;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口：限制某段时间内的TPS
 * 优点：简单暴力
 * 缺点：无法处理时间边界突增的请求，比如限制是10秒内100次请求，而在最后的1s突然来了100个请求，并且下一时间区间的第一秒也来了100个请求，
 *      这样相当于服务在2s内需要处理200个请求，高于限制的100个
 * @author caichengcheng
 * date:2019-12-09
 */
public class FixedWindowRateLimit implements IMyRateLimit {
    //每个时间窗口的限制数
    private int countLimit = 3;
    //最近一个时间窗口的起始时间
    private volatile long lastTime = System.currentTimeMillis();
    //最近一个时间窗口已处理请求数量
    private AtomicInteger currentCount = new AtomicInteger();

    @Override
    public boolean isOverLimit() {
        return currentCount.get() > countLimit;
    }

    @Override
    public boolean visit() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (FixedWindowRateLimit.class){
            if(currentTimeMillis - lastTime > 1000){
                lastTime = currentTimeMillis;
                currentCount.set(0);
            }
        }
        currentCount.getAndIncrement();
        return isOverLimit();
    }

    @Override
    public int currentQps() {
        return currentCount.get();
    }


    /**
     * 允许结果如下：可以看出每一轮，都只允许3个请求执行
     * 线程1:false
     * 线程3:true
     * 线程2:true
     * 线程4:false
     * 线程0:false
     * =========round 2==========
     * 线程4:false
     * 线程2:true
     * 线程1:false
     * 线程0:false
     * 线程3:true
     */
    @Test
    public void test2(){
        FixedWindowRateLimit fixedWindowRateLimit = new FixedWindowRateLimit();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for(int i = 0;i<5;i++){
            Thread t = new Thread(()->{
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + ":"+fixedWindowRateLimit.visit());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            t.setName("线程"+i);
            t.start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("=========round 2==========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<5;i++){
            Thread t = new Thread(()->{
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + ":"+fixedWindowRateLimit.visit());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            t.setName("线程"+i);
            t.start();

        }

    }
}
