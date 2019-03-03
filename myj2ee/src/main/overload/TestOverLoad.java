package overload;

/**
 * 方法重载
 * create by caichengcheng
 * date:2019-02-26
 */
public class TestOverLoad {

    static abstract class human{
    }
    static class man extends human {
    }
    static class woman extends human {
    }
    public void sayHello(human guy){
        System.out.println("hello guy");
    }
    public void sayHello(man guy){
        System.out.println("hello gentleman");
    }
    public void sayHello(woman guy){
        System.out.println("hello lady");
    }
    public static void  main(String[] args){
        human man = new man();
        human woman = new woman();
        TestOverLoad test  = new TestOverLoad();
        test.sayHello(man);
        test.sayHello(woman);
        man man2 = new man();
        woman woman2 = new woman();
        test.sayHello(man2);
        test.sayHello(woman2);
    }
    /**
     * 执行结果：
     * hello guy
     * hello guy
     * hello gentleman
     * hello lady
     * 说明：三个方法构成重载，在进行编译的时候，case1和case2因为声明的时候是human类型，此时静态类型（外观类型）是父类human，
     * 静态类型是在编译器已知的；实际使用的是man或者woman是在运行时确定的，称为实际类型,虚拟机（准确的说是编译机）在重载的时候
     * 是通过参数的静态类型而不是实际类型作为判断依据。
     */
}


