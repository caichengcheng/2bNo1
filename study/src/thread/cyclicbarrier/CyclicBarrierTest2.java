package thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * create by caichengcheng
 * date:2019-01-25
 */
public class CyclicBarrierTest2 {
    /**
     * 构造器中的Runnable，那么当有线程到达barrier的时候，优先执行runnable
     * 如下代码最终执行结果为：
     * runA 执行
     * 线程1 执行
     * 主线程执行
     */
    static CyclicBarrier c = new CyclicBarrier(2,new RunA());

    public static void main(String[] args) {
        new Thread(()->{

            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程1 执行");

        }).start();
        try {
            c.await();
            System.out.println("主线程执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class RunA implements  Runnable{

        @Override
        public void run() {
            System.out.println("runA 执行");
        }
    }
}
