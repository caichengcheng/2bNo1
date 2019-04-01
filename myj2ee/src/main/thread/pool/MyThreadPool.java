package thread.pool;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create by caichengcheng
 * date:2019-03-16
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.HOURS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println(threadPoolExecutor.getPoolSize());

        threadPoolExecutor.execute(()->{
            System.out.println("子线程执行");
        });
         System.out.println(threadPoolExecutor.getPoolSize());
        try {
            String s = threadPoolExecutor.submit(() -> {
                return "abc";
            }).get();
            System.out.println(s);
        }catch (Exception e){

        }
        new HashMap<>();
    }
}
