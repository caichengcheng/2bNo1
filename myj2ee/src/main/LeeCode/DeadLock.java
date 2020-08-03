package LeeCode;

public class DeadLock {

    public static void main(String[] args) {
        new DeadLockClass(true).start();
        new DeadLockClass(false).start();
    }

    static class DeadLockClass extends Thread{
        private boolean flag ;
        private static Object lock1 = new Object();
        private static Object lock2 = new Object();
        public DeadLockClass(boolean flag){
            this.flag = flag;
        }

        @Override
        public void run(){
            if(flag){
                synchronized (lock1){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println("i am not dead!!");
                    }
                }
            }else{
                synchronized (lock2){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("i am not dead!!");
                    }
                }
            }
        }

    }
}
