package thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 使用fork-join 框架1+2+3+4 分段计算demo
 * create by caichengcheng
 * date:2019-07-06
 */
public class CountTask extends RecursiveTask<Integer> {
    //执行阈值
    private static final int HOLD = 2;
    //任务开始
    private int start;
    //结束点
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 里再次解释下为什么继承 RecursiveTask
     * fork-join框架，主要是抽象类ForkJoinTask的两个子类来实现：
     *      RecursiveTask：有返回值(注意在继承的时候加泛型)
     *      RecursiveAction:没有返回值
     * 而这两个子类，都提供了抽象方法compute（）,让开发者去自己实现内部的业务逻辑
     * @return
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        //任务量是否大于自己定义的阈值（大于则判定是大任务，需要fork-join）
        boolean canCompute = (end - start) >>> 1 <= HOLD ;
        System.out.println(start+","+end);
        if(canCompute){
            for (int i = start;i<=end ;i++){
                sum += i;
            }
        }else {
            int middle = (start + end) >>> 1;
            CountTask left = new CountTask(start, middle);
            CountTask right = new CountTask(middle + 1, end);
            left.fork();
            right.fork();
            Integer leftResult = left.join();
            Integer rightResult = right.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //execute无返回值
//        forkJoinPool.execute(new CountTask(1,8));
        //submit有返回值
        ForkJoinTask submit = forkJoinPool.submit(new CountTask(1, 8));
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
