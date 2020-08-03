package LeeCode;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreExer {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(()->{
                try {

                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"停进了车位");
                    Random random = new Random();
                    Thread.sleep(500*random.nextInt(10));

                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"驶出了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"车辆"+i).start();

        }

    }
}
