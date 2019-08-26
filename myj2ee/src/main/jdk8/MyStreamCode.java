package jdk8;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * create by caichengcheng
 * date:2019-03-28
 *
 *
 */
public class MyStreamCode {

    public static List<Integer> data = new ArrayList();

    @Before
    public void before(){
        for(int i=0;i<5;i++)
            data.add(i);
    }

    /**
     * 1.生成流的方式：stream()  or parallelStream()
     * 二者的实现是一模一样的  StreamSupport.stream(spliterator(), false);
     */
    @Test
    public void getStream(){
        Stream stream1 = data.stream();
        Stream stream2 = data.parallelStream();
    }

    /**
     * 2.forEach(Consumer<? super T> action)
     * 功能：迭代
     * 参数：Consumer 函数式接口，代表了接受一个输入参数并且无返回的操作
     */
    @Test
    public void testStreamForeach(){
        //case1 : 打印
        data.stream().forEach(System.out::print);
        System.out.println();
        //case2 : 每个元素加1，并打印
        data.stream().forEach(n->{
                n++ ;
                System.out.print(n);});
    }

    /**
     * 3.map(Function<? super T, ? extends R> mapper)
     * 功能：方法用于映射每个元素到对应的结果，处理后的数据单独存储，不影响原数据
     */
    @Test
    public void testStreamMap(){
        System.out.println("map前");
        data.stream().forEach(System.out::print);
        System.out.println("\nmap时打印");
        data.stream().map(n -> n*n).forEach(System.out::print);
        System.out.println("\nmap后");
        data.stream().forEach(System.out::print);
        //下面拿foreach进行对比，可以看出foreach是直接在原数据基础上进行遍历修改，而map（）后，不影响原数据
        System.out.println("\nforeach");
        data.stream().forEach(n->{n=n*n;
            System.out.print(n);
        });

    }

    /**
     * 4.distinct()
     * 功能：去重
     * 去重标准：先比较hashcode()，再比较equals()
     */
    @Test
    public void testStreamDistinct(){
        data.add(1);
        data.add(2);
        data.stream().forEach(System.out::print);
        System.out.println("\n----------");
        data.stream().distinct().forEach(System.out::print);
        System.out.println();
        data.stream().forEach(System.out::print);
    }

    /**
     * 5.sorted(Comparator<? super T> comparator)
     * 功能：按照参数里的规则进行排序
     */
    @Test
    public void testStreamSorted(){
        data.add(2,21);
        data.add(88);
        data.stream().sorted((a,b)->{ return a-b;}).forEach(System.out::print);
        System.out.println();
        data.stream().forEach(System.out::print);
    }

}
