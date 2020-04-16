package proxy.dynamicproxy;

import beancopy.Friend;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author caichengcheng
 * date:2020-04-08
 */
public class CglibProxy<T> implements MethodInterceptor {
    Object target;


    public  Object getProxyInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务");
        Object result = method.invoke(target, objects);
        System.out.println("提交事务");
        return result;
    }

    public static void main(String[] args) {
        Friend friend = new Friend("Tom");
        CglibProxy tom = new CglibProxy();
        Friend proxyInstance = (Friend)tom.getProxyInstance(friend);
        proxyInstance.say();
    }
}
