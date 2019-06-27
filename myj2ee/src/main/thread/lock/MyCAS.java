package thread.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * 自己实现一个CAS自旋，进行线程安全的int++
 * create by caichengcheng
 * date:2019-06-27
 */
public class MyCAS {
    private static Unsafe unsafe ;
    private volatile int value;
    private static long valueOffset;
    static {
        try {
            //注：这里为什么不和AtomicInteger等类类似，使用Unsafe unsafe = Unsafe.getUnsafe(); 因为内部getUnsafe内部会校验安全性，抛出异常
            Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            unsafe =  (Unsafe) theUnsafeInstance.get(Unsafe.class);
            valueOffset = unsafe.objectFieldOffset(MyCAS.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int add(){
        int result ;
        do{
            //这里使用getIntVolatile而不是用getInt  是因为前者是volatile写，能刷新主内存，同时使其他线程的本地缓存失效
            result = unsafe.getIntVolatile(this, valueOffset);

        }while(!unsafe.compareAndSwapInt(this,valueOffset,result,result+1));
        return result;
    }
    public int addUnSafe(){
        
        return value++;
    }
    public int get(){
        return value;
    }
    public static void main(String[] args) {
        MyCAS myCAS = new MyCAS();
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }
//                int add = myCAS.add();
                int add = myCAS.addUnSafe();
                System.out.println(Thread.currentThread().getName()+":"+add);
            },"线程"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(myCAS.get());
    }
}
