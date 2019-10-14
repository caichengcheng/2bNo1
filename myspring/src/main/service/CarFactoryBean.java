package service;

import org.springframework.beans.factory.FactoryBean;

/**
 * 我们在配置Car时，Car的每个属性分别对应一个<property>元素标签。假设我们嫌这种方式不苟简洁，
  而希望通过逗号分隔的方式一次性的为Car的所有属性制定配置值，那么我们可以通过编写一个FactroyBean来达到目的
 * create by caichengcheng
 * date:2019-10-12
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String detail;
    @Override
    public Car getObject() throws Exception {

        System.out.println("进入CarFactoryBean 的getObject");
        String[] split = detail.split(";");
        Car car = new Car();
        car.setName(split[0]);
        car.setPrice(Double.valueOf(split[1]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("进入CarFactoryBean 的getObjectType");
        return IUserService.class;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
