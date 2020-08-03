package LeeCode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExer {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐7颗龙珠了！！！");
        });
        for (int i = 1; i < 8; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    Thread.sleep(100);
                    System.out.println(finalI +"星龙珠找到啦");
                    cyclicBarrier.await();//阻塞当前进程

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"Thread"+i).start();
        }


    }
}
