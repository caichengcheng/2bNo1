package thread.queue;

/**
 * create by caichengcheng
 * date:2019-05-19
 */
public class Productor implements Runnable {
    private BlockingQueue blockQueue;
    private int sequence;

    public Productor(BlockingQueue blockQueue, int sequence) {
        this.blockQueue = blockQueue;
        this.sequence = sequence;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread Name:" + Thread.currentThread().getName() + " put:" + sequence);
            blockQueue.put(sequence);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
