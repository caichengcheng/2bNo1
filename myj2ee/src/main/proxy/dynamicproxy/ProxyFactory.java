package proxy.dynamicproxy;


import proxy.IUserDao;
import proxy.staticproxy.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 *
 * 引入：静态代理虽然保证了业务类只需要关注逻辑本身，代理对象的一个个口只服务于一种类型的对象，
 * 换而言之，如果要代理的方法很多，代理对象本身就要实现所有的接口，然后给每一个接口做代理，故缺点如下
 * 1. 代理对象要实现所有 目标对象所实现的接口
 * 2. 若出现接口变动，目标对象接口要进行变动，切所有的代理类也要进行接口变动，不易于扩展和维护
 *
 *
 * 动态代理模式：
 * 时机：在程序运行期间，通过JVM的反射等机制动态生成代理类
 * 本质：使用代理模式，生成了.class的二进制字节码，并写到硬盘上
 *
 *
 *
 *
 * 代理工厂，给多个目标对象生成代理对象
 * create by caichengcheng
 * date:2019-02-19
 */
public class ProxyFactory {
    //目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//目标对象使用的类加载器
                target.getClass().getInterfaces(),//目标对象实现的所有接口类的二进制文件集合
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //proxy 代理对象，method 代理方法，args 方法参数
                        System.out.println("开启事务。");
                        Object result = method.invoke(target, args);
                        System.out.println("提交事务");
                        return result;
                    }
                }
        );
        return proxy;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        System.out.println("目标对象"+userDao.getClass());
        IUserDao proxyInstance = (IUserDao)proxyFactory.getProxyInstance();
        System.out.println("代理对象"+proxyInstance.getClass());
        proxyInstance.find();
    }
}
