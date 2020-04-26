package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * create by caichengcheng
 * date:2019-03-28
 *
 *
 * 函数式接口编程，在java.springall.util.function 包下定义的函数式接口
 * 	1.BiConsumer<T,U>  ： 代表了一个接受两个输入参数的操作，并且不返回任何结果
 * 	2.BiFunction<T,U,R>  ：代表了一个接受两个输入参数的方法，并且返回一个结果
 * 	3.BinaryOperator<T>  ：代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果
 * 	4.BiPredicate<T,U>  ：代表了一个两个参数的boolean值方法
 * 	5.BooleanSupplier  ： 代表了boolean值结果的提供方
 * 	6.Consumer<T>  ：代表了接受一个输入参数并且无返回的操作
 * 	7.DoubleBinaryOperator ：代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。
 * 	8.DoubleConsumer ：代表一个接受double值参数的操作，并且不返回结果。
 * 	9.DoubleFunction<R> ：代表接受一个double值参数的方法，并且返回结果
 * 	10.DoublePredicate ：代表一个拥有double值参数的boolean值方法
 * 	11.DoubleSupplier ：代表一个double值结构的提供方
 * 	12.DoubleToIntFunction ：接受一个double类型输入，返回一个int类型结果。
 * 	13.DoubleToLongFunction :接受一个double类型输入，返回一个long类型结果
 * 	14.DoubleUnaryOperator:接受一个参数同为类型double,返回值类型也为double
 * 	15.Function<T,R> :接受一个输入参数，返回一个结果。
 * 	.....
 * 	35.(重点关注)Predicate<T> : 接受一个输入参数，返回一个布尔值结果。
 * 	36.Supplier<T> : 无参数，返回一个结果。
 * 	......
 * 	43.UnaryOperator<T>  : 接受一个参数为类型T,返回值类型也为T。
 */
public class TestFunctionMethod {
    /**
     * 如这样的针对集合的处理，处理的策略是可变的，实现高可用
     * @param list
     * @param checkStrategy
     */
    private void check(List<Integer> list , Predicate<Integer> checkStrategy){
        for(Integer item : list) {
            if(checkStrategy.test(item)){
                System.out.println(item+"元素校验通过");
            }
        }
    }
    @Test
    public void test1(){
        List<Integer> data = new ArrayList();
        for(int i=0;i<5;i++)
            data.add(i);

        TestFunctionMethod instance = new TestFunctionMethod();
        instance.check(data,n-> n%2 == 0);

        //或者使用stream的filter
        Stream<Integer> integerStream = data.stream().filter(n -> n % 2 == 0);
        integerStream.forEach(System.out::println);
    }
}
