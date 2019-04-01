package overload;

/**
 * 方法重写：子类和父类的方法名，返回值，参数列表完全相同，构成重写，重写的方法的作用域关键字范围要不小于父类的被重写方法
 * create by caichengcheng
 * date:2019-02-26
 */
public class TestWrite {
    public static void main(String[] args){
        A ab = new B();
        B b = new B();
        C c = new C();
        System.out.println(ab.show(b));
        System.out.println(ab.show(c));

        /**
         * 执行结果：
         * B and A
         * B and A
         *
         * 说明:继承关系 C -> B -> A ,重写的方法为 String show(A obj)；
         * case1的执行过程为：ab.show(b)，先找到父类A中的show(A obj)-编译期间，在执行期间，发现了子类B中有重写的方法，所以调用的是重写的方法，故
         * 输出B and A
         * case2的执行过程为：ab.show(c)，同case1;为什么不调用show(C obj)呢？ 因为编译器就确定了是使用show(A obj)，运行时只会去判断是否有重写，
         * 而不会考虑重载的情况
         */
    }
    static class A {
        public String show(D obj){
            return ("A and D");
        }
        protected String show(A obj){
            return ("A and A");
        }

    }
    static class B extends A{
        public String show(A obj){
            return ("B and A");
        }
        public String show(B obj){
            return ("B and B");
        }
        public String show(C obj){
            return ("B and C");
        }
    }
    static class C extends B{}
    static class D extends B{}
}

