package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author caichengcheng
 * date:2019-12-05
 */
@Component
//@Scope(value = "prototype")
@Lazy
public class Persion {
//    private Car c = SpringInit.getBean(Car.class);
//    @Autowired
    private Car c;

//    public Persion() {
//    }

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }
}
