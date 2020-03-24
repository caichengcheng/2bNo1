package thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * AB交替打印
 * @author caichengcheng
 * date:2020-03-10
 */
public class ABABprintByWait {
    public static volatile int flag = 0;
    public  synchronized void printA(){
        try {
            if(flag != 0){
                this.wait();
            }
            System.out.println("A");
            flag = 1;
            TimeUnit.SECONDS.sleep(1);
            this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  synchronized void printB(){
        try {
            if(flag != 1){
                this.wait();
            }
            System.out.println("B");
            flag = 0;
            TimeUnit.SECONDS.sleep(1);
            this.notify();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ABABprintByWait obj = new ABABprintByWait();
        Thread t1 = new Thread(()->{
            while(true){
                obj.printA();
            }
        });
        Thread t2 = new Thread(()->{
            while(true){
                obj.printB();
            }
        });
        t1.start();
        t2.start();
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
