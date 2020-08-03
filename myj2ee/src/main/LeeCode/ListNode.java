package LeeCode;

import com.fasterxml.jackson.core.TreeNode;
import com.google.common.collect.MinMaxPriorityQueue;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 链表算法题
 */
public class ListNode {
    /**
     * 1.反转一个单链表。
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */





    @Test
    @Bean
    @Scope()
    public void testPow(){
//        double v = myPow(0.00001, 2147483647);
//        System.out.println(v);
    }

    @Test
    public void reverseList(){
        String s = "1231235436dsf";

        Stack stack = new Stack();
        stack.push("(");
        System.out.println("(".equals(stack.peek()));
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(1,(o1, o2) -> o2 - o1);
        int[] ints = new int[4];
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> intRes = new ArrayList<>();
        for (List<Integer> intRe : intRes) {

        }
        Arrays.sort(ints);
        for (int anInt : ints) {

        }



        System.out.println(stack.isEmpty());
        Map<Character,Integer> map = new HashMap();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            entry.getKey();
        }
        int sum = 1;




        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println("======"+deque.peekFirst());
        System.out.println("======"+deque.poll());
        System.out.println("======"+deque.peekLast());

        ExecutorService executorService = Executors.newFixedThreadPool(199);

        int x=0 ,y=5;
        int z = (x+y)/2;
        System.out.println("=---------"+z);




    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer = new Producer(resource,"producer");

        Consumer consumer = new Consumer(resource, "consumer1");

        producer.start();
        consumer.start();
    }

    static class Consumer extends Thread{
        private Resource resource;
        public Consumer(Resource resource,String name){
            this.resource = resource;
            this.setName(name);
        }

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.remove();
            }

        }
    }

    static class Producer extends Thread{
        private Resource resource;
        public Producer(Resource resource,String name){
            this.resource = resource;
            this.setName(name);
        }

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.add();
            }

        }
    }

    static class Resource{
        private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue(10);

        public void add(){
            queue.offer(1);
            System.out.println("生产者:"+Thread.currentThread().getName()+"生产了一件资源，当前资源数："+queue.size());
        }

        public void remove(){
            queue.poll();
            System.out.println("消费者:"+Thread.currentThread().getName()+"消费了一件资源，当前资源数："+queue.size());
        }
    }

}
