package thread.basic;

/**
 * ThreadLocal:通一个线程，若跨越不同class和不同的method，又需要有个共同的变量传递使用，可以使用threadlocal
 * 进行线程内通信，主要方法为set，get,remove
 * 注意点:
 * 对于线程池而言，若使用了threadlocal，会出现threadlocal 中的数据混乱使用的情况，顾若使用了线程池情况下，要对
 * threadlocal 进行操作，最终必须remove 进行清空
 * create by caichengcheng
 * date:2019-02-26
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Long> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.set(1L);
        System.out.println(objectThreadLocal.get());
        objectThreadLocal.remove();
        System.out.println(objectThreadLocal.get());
        objectThreadLocal.set(2L);
        System.out.println(objectThreadLocal.get());


        ThreadLocal<Long> tl2 = new ThreadLocal<>();
        tl2.set(3L);
        System.out.println(objectThreadLocal.get());
        System.out.println(tl2.get());

    }
}
