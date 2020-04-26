package springall.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import springall.util.SpringInit;

/**
 * @author caichengcheng
 * date:2019-12-05
 */
@Component
//@Scope(value = "prototype")
@Lazy
public class Persion {
    private Car c = SpringInit.getBean(Car.class);
//    @Autowired
//    private Car c;

//    public Persion() {
//    }

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }
}
