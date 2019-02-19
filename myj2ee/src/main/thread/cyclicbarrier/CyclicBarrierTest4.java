package thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * create by caichengcheng
 * date:2019-01-25
 */
public class CyclicBarrierTest4 {
    static CyclicBarrier c  = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try{
                c.await();
                System.out.println("线程1的执行结束");
            }catch (Exception e){

            }
        });
        t.start();
        //优雅的中断一个线程，为何优雅？使用 被中断线程的isInterrupted()方法，做被中断后的处理
        t.interrupt();
        try{
            c.await();
        }catch (Exception e){

        }
        System.out.println("线程t是否被中断？  结果："+t.isInterrupted());
        System.out.println("同步屏障的阻塞线程数量："+ c.getNumberWaiting());
        System.out.println("同步屏障中的阻塞线程是否被中断了："+c.isBroken());
    }
}
