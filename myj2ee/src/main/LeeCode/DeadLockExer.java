package LeeCode;

public class DeadLockExer {

    public static void main(String[] args) {
        DeadLockResource deadLockResource1 = new DeadLockResource(true);
        DeadLockResource deadLockResource2 = new DeadLockResource(false);
        new Thread(()->{
            deadLockResource1.deadRun();
        }).start();
        new Thread(()->{
            deadLockResource2.deadRun();
        }).start();
    }

    static class DeadLockResource{
        private static Object lock1 = new Object();
        private static Object lock2 = new Object();
        private boolean flag;
        public DeadLockResource(boolean flag){
            this.flag = flag;
        }

        public void deadRun(){
            if(flag){
                synchronized (lock1){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock2){
                        System.out.println("i am not dead!");
                    }
                }
            }else {
                synchronized (lock2){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock1){
                        System.out.println("i am not dead!");
                    }
                }
            }
        }
    }
}
