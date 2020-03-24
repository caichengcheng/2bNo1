package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * create by caichengcheng
 * date:2019-10-12
 */
@Component
//@Scope(value = "prototype")
public class Car {
//    @Autowired
    private Persion p;
    private String name;
    private double price;

    public Car() {
    }
//    @Autowired
    public Car(Persion p, String name, double price) {
        this.p = p;
        this.name = name;
        this.price = price;
    }

    public Persion getP() {
        return p;
    }

    public void setP(Persion p) {
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
