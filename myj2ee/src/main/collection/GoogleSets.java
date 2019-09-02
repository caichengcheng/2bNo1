package collection;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * 使用Sets 工具类，实现求两个集合的交集、并集。。。
 * create by caichengcheng
 * date:2019-08-29
 */
public class GoogleSets {
    @Test
    public void test1(){
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        Set<String> result1 = Sets.union(set1, set2);//合集，并集

        Set<String> result2 = Sets.intersection(set1, set2);//交集

        Set<String> result3 = Sets.difference(set1, set2);//差集 1中有而2中没有的

        Set<String> result4 = Sets.symmetricDifference(set1, set2);//相对差集 1中有2中没有 2中有1中没有的 取出来做结果
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> objects = new PriorityQueue<>();
        objects.add(5);
        objects.add(7);
        objects.add(9);
        objects.add(1);
        objects.add(3);
        objects.add(10);
        objects.add(8);
        objects.add(4);
        System.out.println(objects);
        Random random = new Random();

        for(int i = 0;i<10;i++){
            System.out.println(random.nextInt(2));
        }

    }
}
