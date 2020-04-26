package springframwork;

import springall.bean.MyFactoryBean;
import springall.bean.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springall.service.Car;
import springall.service.IUserService;
import springall.service.MessageService;

/**
 * create by caichengcheng
 * date:2019-09-02
 */
public class SpringTest {

    @Test
    public void ioc(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = applicationContext.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());

//        Car springall.bean = (Car)applicationContext.getBean("car");


    }

    /**
     * spring 配置的三种方式：xml、注解、java代码
     * 这里展示的是java代码
     */
    @Test
    public void testConfigByJava(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyFactoryBean.class);
        Student student = context.getBean(Student.class);
        System.out.println(student);
        student.say();
        Student student2 = context.getBean(Student.class);
        System.out.println(student2);
    }
}
