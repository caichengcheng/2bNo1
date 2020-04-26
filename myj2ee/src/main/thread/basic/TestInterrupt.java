package thread.basic;

import java.util.concurrent.TimeUnit;

/**
 *
 * create by caichengcheng
 * date:2019-02-26
 *
 *
 * 输出结果：
 * false
 * true
 * java.lang.InterruptedException: sleep interrupted
 * 	at java.lang.Thread.sleep(Native Method)
 * 	at java.lang.Thread.sleep(Thread.java:340)
 * 	at java.springall.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
 * 	at thread.basic.SleepRunner.run(TestInterrupt.java:33)
 * 	at java.lang.Thread.run(Thread.java:748)
 *
 * 	都被中断后，可见忙碌的线程的中断标识是true,而沉睡线程的中断标识是false,原因是sleep方法抛异常
 * 	InterruptedException：sleep interrupted  ，然后重置了中断标识
 */
public class TestInterrupt {
    public static void main(String[] args) {
        Thread sleepRunner = new Thread(new SleepRunner(),"sleepRunner");
        Thread busyRunner = new Thread(new BusyRunner(),"busyRunner");
        sleepRunner.start();
        busyRunner.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepRunner.interrupt();
        busyRunner.interrupt();
        System.out.println(sleepRunner.isInterrupted());
        System.out.println(busyRunner.isInterrupted());

    }
    static class SleepRunner implements  Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true){
            }
        }
    }

}
