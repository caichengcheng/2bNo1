package springall.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.TimeUnit;

@ComponentScan("springall.aop")
@EnableAspectJAutoProxy
public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(AopTest.class);
        BService bean = ioc.getBean(BService.class);
        bean.doSomething();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-------");
        IBusinessService service = ioc.getBean(IBusinessService.class);
        service.checkPrarm();
    }
}
