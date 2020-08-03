package LeeCode;

import java.util.concurrent.Callable;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableExer {

    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Demo());
        new Thread(futureTask,"aa").start();
        Integer integer = null;
        try {
            integer = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("main i am waiting,and the result is  " + integer);
    }

    static class Demo implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("hello");
            Thread.sleep(4000);
            return 200;
        }
    }
}
