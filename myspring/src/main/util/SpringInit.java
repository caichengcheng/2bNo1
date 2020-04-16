package util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by daojia on 2018/4/16.
 */
@Component
public class SpringInit implements ApplicationContextAware {

    private static ApplicationContext beanFactory;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanFactory=applicationContext;
    }


    public static  <T> T getBean(String name){
        return (T)beanFactory.getBean(name);
    }

    public static  <T> T getBean(Class clazz){
        return (T)beanFactory.getBean(clazz);
    }
}
