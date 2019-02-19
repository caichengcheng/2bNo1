package thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * create by caichengcheng
 * date:2019-01-26
 *
 * 这里模拟了需要10个线程去争夺5个令牌的过程：使用于两种资源数量不均衡，需要做适配处理的情况，如需要10次对数据库的操作，但是数据库连接池
 * 只有5个连接
 * 结果： 5个令牌都被占用，后续只有获取到令牌的线程释放令牌，下一个线程才能执行业务
 */
public class SemaphoreTest1 {
    private static final int THREAD_COUNT = 10;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i=0;i<THREAD_COUNT ; i++){
            System.out.println("线程"+i+"开始，尝试去获取令牌,此时令牌池中剩余：");
            final int k = i;
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println("线程"+k+"获取到令牌，处理业务");
                    Thread.currentThread().sleep((int)(1+Math.random()*1000));
                    System.out.println("线程"+k+"处理业务完毕，开始释放令牌");
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println("主线程执行结束！");
    }

}
