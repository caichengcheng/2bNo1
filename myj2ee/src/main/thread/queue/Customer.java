package thread.queue;

/**
 * create by caichengcheng
 * date:2019-05-19
 */
public class Customer implements Runnable{
    private BlockingQueue blockQueue;

    public Customer(BlockingQueue blockQueue) {
        this.blockQueue = blockQueue;
    }

    @Override
    public void run() {
        try {
            Object result = blockQueue.get();
            System.out.println("Thread Name:" + Thread.currentThread().getName() + " get:" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
