package LeeCode;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExer<T,S> {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"--sleep end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            },"Thread"+i).start();
        }
        try {
            System.out.println("main begin await:"+System.currentTimeMillis());
            countDownLatch.await();
            System.out.println("main end await:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    
}
